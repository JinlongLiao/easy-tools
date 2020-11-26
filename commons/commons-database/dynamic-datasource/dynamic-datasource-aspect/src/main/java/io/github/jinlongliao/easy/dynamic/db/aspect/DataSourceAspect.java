/*
 *  Copyright 2018-2020 , 廖金龙 (mailto:jinlongliao@foxmail.com).
 * <p>
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package  io.github.jinlongliao.easy.dynamic.db.aspect;

import io.github.jinlongliao.easy.dynamic.db.core.annotation.DbDataSource;
import io.github.jinlongliao.easy.dynamic.db.core.config.ThreadConfig;
import io.github.jinlongliao.easy.dynamic.db.core.constant.KeyConstant;
import io.github.jinlongliao.easy.dynamic.db.core.interceptor.Callback;
import io.github.jinlongliao.easy.dynamic.db.core.interceptor.MethodInterceptor;
import io.github.jinlongliao.easy.dynamic.db.core.interceptor.factory.MethodInterceptorFactory;
import io.github.jinlongliao.easy.dynamic.db.core.util.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * @author liaojinlong
 * @since 2020/9/17 12:53
 */
@Aspect
public class DataSourceAspect {
  @Pointcut("@annotation(io.github.jinlongliao.easy.dynamic.db.core.annotation.DbDataSource)")
  public void dbDataSourceAnnotationPointcut() {
  }

  @Around("dbDataSourceAnnotationPointcut()")
  public Object invokeResourceWithSentinel(ProceedingJoinPoint pjp) throws Throwable {
    final Object target = pjp.getTarget();
    final Object[] args = pjp.getArgs();
    Method originMethod = resolveMethod(pjp);
    final DbDataSource dbDataSource = originMethod.getAnnotation(DbDataSource.class);
    final Class<? extends Callback> callbackClass = dbDataSource.dbCall();
    final List<MethodInterceptor> methodInterceptorByAnnotation = MethodInterceptorFactory
            .getInstance()
            .getMethodInterceptorByAnnotation(DbDataSource.class);
    Object result = null;
    for (MethodInterceptor methodInterceptor : methodInterceptorByAnnotation) {
      if (!callbackClass.equals(Callback.class)) {
        methodInterceptor.setCallback(callbackClass.newInstance());
      } else {
        methodInterceptor.setCallback(new Callback() {
          @Override
          public Object doFinally(Object result, Annotation annotation, Object proxy, Method method, Object[] args) {
            Connection connection = null;
            try {
              result = pjp.proceed();
            } catch (Throwable throwable) {
              try {
                connection = ThreadConfig.getInstance().getConnection();
                if (Objects.nonNull(connection) && !connection.isClosed()) {
                  connection.rollback();
                  connection.close();
                }
              } catch (SQLException sqlException) {
                throw new RuntimeException(sqlException);
              }
              throw new RuntimeException(throwable);
            } finally {
              connection = ThreadConfig.getInstance().getConnection();
              try {
                if (Objects.nonNull(connection) && !connection.isClosed()) {
                  connection.close();
                }
              } catch (SQLException sqlException) {
                throw new RuntimeException(sqlException);
              }
              ThreadConfig.getInstance().clear();
            }
            return result;
          }

          @Override
          public void before(Annotation annotation, Object proxy, Method method, Object[] args) {
            DbDataSource dataSource = (DbDataSource) annotation;
            String dbKey = dataSource.dbKey();
            if (StringUtils.isEmpty(dbKey)) {
              dbKey = dataSource.value();
            }
            final ThreadConfig threadConfig = ThreadConfig.getInstance();
            final boolean inSubThread = dataSource.scope().equals(DbDataSource.Scope.ALL);
            threadConfig.setBoolean(KeyConstant.IN_SUB_THREAD, inSubThread, true);
            threadConfig.setDbKey(dbKey, inSubThread);
          }
        });
      }
      methodInterceptor.setInvoke(Boolean.FALSE);
      result = methodInterceptor.invoke(target, originMethod, args);
    }
    return result;
  }

  protected Method resolveMethod(ProceedingJoinPoint joinPoint) {
    final Object target = joinPoint.getTarget();
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Class<?> targetClass = target.getClass();
    Method method = getDeclaredMethodFor(targetClass, signature.getName(),
      signature.getMethod().getParameterTypes());
    if (method == null) {
      throw new IllegalStateException("Cannot resolve target method: " + signature.getMethod().getName());
    }
    return method;
  }

  /**
   * Get declared method with provided name and parameterTypes in given class and its super classes.
   * All parameters should be valid.
   *
   * @param clazz          class where the method is located
   * @param name           method name
   * @param parameterTypes method parameter type list
   * @return resolved method, null if not found
   */
  private Method getDeclaredMethodFor(Class<?> clazz, String name, Class<?>... parameterTypes) {
    try {
      return clazz.getDeclaredMethod(name, parameterTypes);
    } catch (NoSuchMethodException e) {
      Class<?> superClass = clazz.getSuperclass();
      if (superClass != null) {
        return getDeclaredMethodFor(superClass, name, parameterTypes);
      }
    }
    return null;
  }
}
