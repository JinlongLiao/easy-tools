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
package io.github.jinlonghliao.common.aop.interceptor;

import io.github.jinlonghliao.common.aop.aspects.Aspect;
import io.github.jinlonghliao.common.core.util.ClassUtil;
import io.github.jinlonghliao.common.core.util.ReflectUtil;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * JDK实现的动态代理切面
 *
 * @author Looly
 * @author ted.L
 */
public class JdkInterceptor implements InvocationHandler, Serializable {
    private static final long serialVersionUID = 1L;

    private final Object target;
    private final Aspect aspect;

    /**
     * 构造
     *
     * @param target 被代理对象
     * @param aspect 切面实现
     */
    public JdkInterceptor(Object target, Aspect aspect) {
        this.target = target;
        this.aspect = aspect;
    }

    public Object getTarget() {
        return this.target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        final Object target = this.target;
        final Aspect aspect = this.aspect;
        Object result = null;

        // 开始前回调
        if (aspect.before(target, method, args)) {
            ReflectUtil.setAccessible(method);

            try {
                result = method.invoke(ClassUtil.isStatic(method) ? null : target, args);
            } catch (InvocationTargetException e) {
                // 异常回调（只捕获业务代码导致的异常，而非反射导致的异常）
                if (aspect.afterException(target, method, args, e.getTargetException())) {
                    throw e;
                }
            }
        }

        // 结束执行回调
        if (aspect.after(target, method, args, result)) {
            return result;
        }
        return null;
    }

}
