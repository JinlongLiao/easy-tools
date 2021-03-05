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

import io.github.jinlonghliao.common.aop.aspects.Aspect;
import io.github.jinlonghliao.common.core.util.ReflectUtil;
import io.github.jinlonghliao.common.core.util.ServiceLoaderUtil;

import java.io.Serializable;

/**
 * 代理工厂<br>
 * 根据用户引入代理库的不同，产生不同的代理对象
 *
 * @author looly
 */
public abstract class ProxyFactory implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 创建代理
     *
     * @param <T>         代理对象类型
     * @param target      被代理对象
     * @param aspectClass 切面实现类，自动实例化
     * @return 代理对象
     * @since 5.3.1
     */
    public <T> T proxy(T target, Class<? extends Aspect> aspectClass) {
        return proxy(target, ReflectUtil.newInstanceIfPossible(aspectClass));
    }

    /**
     * 创建代理
     *
     * @param <T>    代理对象类型
     * @param target 被代理对象
     * @param aspect 切面实现
     * @return 代理对象
     */
    public abstract <T> T proxy(T target, Aspect aspect);

    /**
     * 根据用户引入Cglib与否自动创建代理对象
     *
     * @param <T>         切面对象类型
     * @param target      目标对象
     * @param aspectClass 切面对象类
     * @return 代理对象
     */
    public static <T> T createProxy(T target, Class<? extends Aspect> aspectClass) {
        return createProxy(target, ReflectUtil.newInstance(aspectClass));
    }

    /**
     * 根据用户引入Cglib与否自动创建代理对象
     *
     * @param <T>    切面对象类型
     * @param target 被代理对象
     * @param aspect 切面实现
     * @return 代理对象
     */
    public static <T> T createProxy(T target, Aspect aspect) {
        return create().proxy(target, aspect);
    }

    /**
     * 根据用户引入Cglib与否创建代理工厂
     *
     * @return 代理工厂
     */
    public static ProxyFactory create() {
        return ServiceLoaderUtil.loadFirstAvailable(ProxyFactory.class);
    }
}
