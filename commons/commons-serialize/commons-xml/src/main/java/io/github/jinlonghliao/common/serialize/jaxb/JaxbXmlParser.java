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
package io.github.jinlonghliao.common.serialize.jaxb;

import io.github.jinlonghliao.common.core.io.IoUtil;
import io.github.jinlonghliao.common.core.util.CharsetUtil;
import io.github.jinlonghliao.common.serialize.XmlParser;
import io.github.jinlonghliao.common.serialize.jackjson.JackJsomXmlParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liaojinlong
 * @since 2020/10/15 17:58
 */
public class JaxbXmlParser implements XmlParser {
    private static final Map<String, JAXBContext> PARSE_CACHE = new ConcurrentHashMap<>(16);
    private static JaxbXmlParser instance = null;

    private JaxbXmlParser() {
    }

    public static JaxbXmlParser getInstance() {
        if (instance == null) {
            //双重检查加锁，只有在第一次实例化时，才启用同步机制，提高了性能。
            synchronized (JaxbXmlParser.class) {
                if (instance == null) {
                    instance = new JaxbXmlParser();
                }
            }
        }
        return instance;
    }

    @Override
    public <T> String java2xml(T data) throws Exception {
        JAXBContext jaxbContext = geJAXBContextt(data.getClass());
        // 创建 Marshaller 实例
        Marshaller marshaller = jaxbContext.createMarshaller();
        // 设置转换参数 -> 这里举例是告诉序列化器是否格式化输出
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        // 构建输出环境 -> 这里使用标准输出，输出到控制台Console
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        // 将所需对象序列化 -> 该方法没有返回值
        marshaller.marshal(data, arrayOutputStream);
        IoUtil.close(arrayOutputStream);
        return new String(arrayOutputStream.toByteArray(), CharsetUtil.CHARSET_UTF_8);
    }

    @Override
    public <T> T xml2java(String xml, Class<T> tClass) throws Exception {
        if (Objects.isNull(tClass)) {
            return (T) JackJsomXmlParser.getInstance().xml2java(xml);
        }
        final Unmarshaller unmarshaller = geJAXBContextt(tClass).createUnmarshaller();
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(xml.getBytes(CharsetUtil.UTF_8));
        return (T) unmarshaller.unmarshal(inputStream);
    }

    /**
     * 静态缓存
     *
     * @param tClass
     * @param <T>
     * @return JAXBContext
     * @throws JAXBException
     */
    private <T> JAXBContext geJAXBContextt(Class<T> tClass) throws JAXBException {
        JAXBContext jaxbContext;

        final String key = tClass.getName();
        if (PARSE_CACHE.containsKey(key)) {
            jaxbContext = PARSE_CACHE.get(key);
        } else {
            jaxbContext = JAXBContext.newInstance(tClass);
            PARSE_CACHE.put(key, jaxbContext);
        }
        return jaxbContext;
    }
}
