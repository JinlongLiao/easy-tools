package io.github.jinlonghliao.common.serialize;


import java.util.Map;

/**
 * @author liaojinlong
 * @since 2020/10/15 17:38
 */
public interface XmlParser {

    /**
     * Java 对象转换为 xml
     *
     * @param data
     * @return XML 字符串
     * @throws Exception
     */
    <T> String java2xml(T data) throws Exception;

    /**
     * 便捷封装
     * \
     *
     * @param xml
     * @return Map
     * @throws Exception
     */
    default Map<String, Object> xml2java(String xml) throws Exception {
        return this.xml2java(xml, null);
    }

    /**
     * XML 反序列化 为 T
     *
     * @param xml
     * @param tClass
     * @param <T>
     * @return T
     * @throws Exception
     */
    <T> T xml2java(String xml, Class<T> tClass) throws Exception;
}
