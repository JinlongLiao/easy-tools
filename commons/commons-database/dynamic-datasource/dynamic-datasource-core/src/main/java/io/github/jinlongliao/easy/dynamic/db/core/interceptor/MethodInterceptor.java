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
package  io.github.jinlongliao.easy.dynamic.db.core.interceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author liaojinlong
 * @since 2020/9/14 16:35
 */
public interface MethodInterceptor<T extends Annotation, K extends Class<T>> {

    /**
     * 获取回调方法
     *
     * @return Callback
     */
    Callback getCallback();

    /**
     * 设置回调方法
     *
     * @param callback 回调方法
     */
    void setCallback(Callback callback);

    /**
     * 是否执行反射
     *
     * @param enableInvoke 是否执行反射
     */
    void setInvoke(Boolean enableInvoke);

    /**
     * 函数 拦截实现
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;

    /**
     * 有效的拦截 Class
     *
     * @return 拦截的注解
     */
    K getAnnotation();

}
