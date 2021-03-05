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
package io.github.jinlonghliao.common.serialize.test;

import io.github.jinlonghliao.common.serialize.XmlParserUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class JaxbTest {
    public static final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> <student><id>22</id><name>jj</name><age>232</age></student>";


    @Test
    public void test1() throws Exception {
        final Student student = new Student();
        student.setAge(232);
        student.setId("22");
        student.setName("jj");
        final String xml = XmlParserUtils.getJaxbXmlParser().java2xml(student);
        System.out.println("xml = " + xml);

    }

    @Test
    public void test2() throws Exception {
        final Student student = XmlParserUtils
                .getJaxbXmlParser()
                .xml2java(xml, Student.class);
        Assert.assertEquals("EQ", student.getId(), "22");
    }

    @Test
    public void ww() {
        String s = "abcabcbb";
        Map<Character, Integer> charCache = new HashMap<>();
        final char[] chars = s.toCharArray();
        int maxSize = 0;
        int start = 0;
        for (int i = 0; i < chars.length; i++) {
            final char aChar = chars[i];
            if (charCache.containsKey(aChar) && charCache.get(aChar) >= start) {
                maxSize = Math.max((i - start), maxSize);
                start = charCache.get(aChar) + 1;
            }
            charCache.put(aChar, i);
        }
        maxSize = Math.max((chars.length - start), maxSize);
        System.out.println(maxSize);
    }
}
