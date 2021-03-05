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
