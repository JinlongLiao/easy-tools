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
package io.github.jinlonghliao.common.aop.proxy;

import io.github.jinlonghliao.common.aop.ProxyUtil;
import io.github.jinlonghliao.common.aop.aspects.Aspect;
import io.github.jinlonghliao.common.aop.interceptor.JdkInterceptor;

/**
 * JDK实现的切面代理
 *
 * @author looly
 */
public class JdkProxyFactory extends ProxyFactory {
    private static final long serialVersionUID = 1L;

    @Override
    @SuppressWarnings("unchecked")
    public <T> T proxy(T target, Aspect aspect) {
        return (T) ProxyUtil.newProxyInstance(
                target.getClass().getClassLoader(),
                new JdkInterceptor(target, aspect),
                target.getClass().getInterfaces());
    }
}
