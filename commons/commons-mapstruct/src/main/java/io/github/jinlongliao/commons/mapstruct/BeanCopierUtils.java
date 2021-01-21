package io.github.jinlongliao.commons.mapstruct;

import io.github.jinlongliao.commons.mapstruct.core.IData2Object;
import io.github.jinlongliao.commons.mapstruct.core.Proxy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Bean 转换
 *
 * @author liaojinlong
 * @since 2020/11/21 23:32
 */

public class BeanCopierUtils {
    /**
     * 实现类缓存
     */
    private final static Map<Class, IData2Object> DATA_2_OBJECT_CACHE = new ConcurrentHashMap<>(1 << 8);
    private static final Proxy PROXY = new Proxy();

    /**
     * 获取数据转换类
     *
     * @param tClass
     * @return IData2Object
     */
    public static IData2Object getData2Object(Class tClass) {
        if (DATA_2_OBJECT_CACHE.containsKey(tClass)) {
            return DATA_2_OBJECT_CACHE.get(tClass);
        }
        synchronized (tClass) {
            if (DATA_2_OBJECT_CACHE.containsKey(tClass)) {
                return DATA_2_OBJECT_CACHE.get(tClass);
            } else {
                final IData2Object proxyObject = PROXY.getProxyObject(tClass);
                DATA_2_OBJECT_CACHE.put(tClass, proxyObject);
                return proxyObject;
            }
        }
    }
}
