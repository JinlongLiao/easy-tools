/**
 * Copyright 2020-2021 JinlongLiao
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.jinlongliao.easy.dynamic.db.core;

import io.github.jinlongliao.easy.dynamic.db.core.annotation.DbDataSource;
import io.github.jinlongliao.easy.dynamic.db.core.config.ThreadConfig;
import io.github.jinlongliao.easy.dynamic.db.core.constant.KeyConstant;
import io.github.jinlongliao.easy.dynamic.db.core.interceptor.Callback;
import io.github.jinlongliao.easy.dynamic.db.core.interceptor.MethodInterceptor;
import io.github.jinlongliao.easy.dynamic.db.core.interceptor.factory.MethodInterceptorFactory;
import org.junit.Assert;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author liaojinlong
 * @since 2020/9/15 18:37
 */
public class DbTest {
  @Test
  public void testDbSource() throws Throwable {
    final DbTest dbTest = new DbTest();
    Method dbSource = null;
    try {
      dbSource = DbTest.class.getMethod("dbSource");
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }
    final Map<String, List<MethodInterceptor>> methodInterceptorByAnnotation = MethodInterceptorFactory
      .getInstance().getMethodInterceptorByAnnotation(dbSource);
    for (String annotation : methodInterceptorByAnnotation.keySet()) {
      if (annotation.equals(DbDataSource.class.getName())) {
        final List<MethodInterceptor> methodInterceptors = methodInterceptorByAnnotation.get(annotation);
        for (MethodInterceptor methodInterceptor : methodInterceptors) {
          methodInterceptor.setCallback(new Callback() {
            @Override
            public Object doFinally(Object result, Annotation annotation, Object proxy, Method method, Object[] args) {
              Assert.assertEquals(" eq ", getDbKey(), "db1");
              return result;
            }

            @Override
            public Object doError(Throwable throwable, Annotation annotation, Object result, Object proxy, Method method, Object[] args) throws Throwable {
              return result;
            }

            @Override
            public Object after(Object result, Annotation annotation, Object proxy, Method method, Object[] args) {
              Assert.assertEquals(" eq ", getDbKey(), "db1");
              return result;
            }

            @Override
            public void before(Annotation annotation, Object proxy, Method method, Object[] args) {
              Assert.assertEquals(" eq ", getDbKey(), "db1");
            }
          });
          methodInterceptor.invoke(dbTest, dbSource, null);
        }
      }
    }
  }

  private Object getDbKey() {
    final Object o = ThreadConfig.getInstance().get(KeyConstant.DB_KEY);
    System.out.println("key: " + o);
    return o;
  }

  @DbDataSource("db1")
  public String dbSource() {
    return super.toString();
  }
}
