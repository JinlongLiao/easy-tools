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
