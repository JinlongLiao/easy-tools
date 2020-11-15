package io.github.jinlonghliao.common.serialize;

import io.github.jinlonghliao.common.serialize.jackjson.JackJsomXmlParser;
import io.github.jinlonghliao.common.serialize.jaxb.JaxbXmlParser;

/**
 * @author liaojinlong
 * @since 2020/10/15 18:18
 */
public class XmlParserUtils {
    private static XmlParserUtils instance = null;

    private XmlParserUtils() {
    }

    /**
     * Jackson 实现
     *
     * @return XmlParser
     */
    public static XmlParser getJackSonXmlParser() {
        return JackJsomXmlParser.getInstance();
    }

    /**
     * Jaxb 实现
     *
     * @return XmlParser
     */
    public static XmlParser getJaxbXmlParser() {
        return JaxbXmlParser.getInstance();
    }

}
