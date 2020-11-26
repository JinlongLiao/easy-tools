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
import java.util.Objects;


/**
 * @author liaojinlong
 * @since 2020/9/14 16:44
 */
public abstract class AbstractMethodInterceptor<T extends Annotation, K extends Class<T>> implements MethodInterceptor {
    protected Callback callback;
    private Boolean enableInvoke = Boolean.TRUE;

    @Override
    public Callback getCallback() {
        return Objects.isNull(callback) ? callback = new DefaultCallback() : callback;
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        final T annotation = getannotation(method);
        try {
            before(annotation, proxy, method, args);
            if (enableInvoke) {
                result = method.invoke(annotation, proxy, args);
            }
            result = after(result, annotation, proxy, method, args);
        } catch (Throwable throwable) {
            result = doError(throwable, annotation, result, proxy, method, args);
        } finally {
            result = doFinally(result, annotation, proxy, method, args);
        }
        return result;
    }

    protected T getannotation(Method method) {
        return (T) method.getDeclaredAnnotation(getAnnotation());
    }

    /**
     * @param annotation
     * @param result
     * @param proxy
     * @param method
     * @param args
     * @return 结果
     */
    protected Object doFinally(Object result, T annotation, Object proxy, Method method, Object[] args) {
        return getCallback().doFinally(result, annotation, proxy, method, args);
    }

    protected Object doError(Throwable throwable, T annotation, Object result, Object proxy, Method method, Object[] args) throws Throwable {
        return getCallback().doError(throwable, annotation, result, proxy, method, args);
    }

    protected Object after(Object result, T annotation, Object proxy, Method method, Object[] args) {
        result = getCallback().after(result, annotation, proxy, method, args);
        return result;
    }

    protected void before(T annotation, Object proxy, Method method, Object[] args) {
        getCallback().before(annotation, proxy, method, args);
    }

    @Override
    public void setInvoke(Boolean enableInvoke) {
        this.enableInvoke = enableInvoke;
    }

    protected class DefaultCallback implements Callback {

    }

}
