/*
 * Copyright 2020-2021.
 * 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
 package io.github.jinlonghliao.common.serialize.jackjson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.github.jinlonghliao.common.serialize.XmlParser;

import java.util.Map;
import java.util.Objects;

/**
 * @author liaojinlong
 * @since 2020/10/15 17:53
 */
public class JackJsomXmlParser implements XmlParser {
    private static JackJsomXmlParser instance = null;

    private JackJsomXmlParser() {
    }

    public static JackJsomXmlParser getInstance() {
        if (instance == null) {
            //双重检查加锁，只有在第一次实例化时，才启用同步机制，提高了性能。
            synchronized (JackJsomXmlParser.class) {
                if (instance == null) {
                    instance = new JackJsomXmlParser();
                }
            }
        }
        return instance;
    }

    XmlMapper xmlMapper = XmlMapper.xmlBuilder().build();

    @Override
    public synchronized <T> String java2xml(T data) throws JsonProcessingException {
        return xmlMapper.writeValueAsString(data);
    }

    @Override
    public synchronized <T> T xml2java(String xml, Class<T> tClass) throws Exception {
        if (Objects.isNull(tClass)) {
            tClass = (Class<T>) Map.class;
        }
        return xmlMapper.readValue(xml, tClass);
    }
}
