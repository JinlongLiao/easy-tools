package io.github.jinlongliao.commons.mapstruct.core;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 将元数据转换为相应的对象
 *
 * @author liaojinlong
 * @since 2020/11/23 21:23
 */

public interface IData2Object {
    /**
     * 转换接口
     *
     * @param data
     * @param <T>
     * @return T
     */
    <T> T toMapConverter(Map<String, Object> data);

    /**
     * Array转换接口(70w以下，性能最优)
     *
     * @param data
     * @param <T>
     * @return T
     */
    <T> T toArrayConverter(Object[] data);

    /**
     * {@link javax.servlet.http.HttpServletRequest}转换接口
     *
     * @param request
     * @param <T>
     * @return T
     */
    <T> T toHttpServletRequestConverter(HttpServletRequest request);
}