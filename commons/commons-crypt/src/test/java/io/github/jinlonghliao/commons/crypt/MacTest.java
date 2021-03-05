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
package io.github.jinlonghliao.commons.crypt;

import io.github.jinlonghliao.commons.crypt.mac.MacEncryptUtils;
import org.junit.Assert;
import org.junit.Test;


public class MacTest {
    @Test
    public void test() throws Exception {
        final String s = new MacEncryptUtils(MacEncryptUtils.H_MAC_MD5, "1313".getBytes())
                .getDigest("123");
        System.out.println(s);
        Assert.assertEquals("EQ", s.length(), 32);
    }

    @Test
    public void test2() throws Exception {
        final String s = new MacEncryptUtils(MacEncryptUtils.H_MAC_MD2, "1313".getBytes())
                .getDigest("123");
        System.out.println(s);
        Assert.assertEquals("EQ", s.length(), 32);
    }

    @Test
    public void test3() throws Exception {
        final String s = new MacEncryptUtils(MacEncryptUtils.H_MAC_MD4, "1313".getBytes())
                .getDigest("123");
        System.out.println(s);
        Assert.assertEquals("EQ", s.length(), 32);
    }

    @Test
    public void test4() throws Exception {
        final String s = new MacEncryptUtils(MacEncryptUtils.H_MAC_SHA1, "1313".getBytes())
                .getDigest("123");
        System.out.println(s);
    }

    @Test
    public void test5() throws Exception {
        final String s = new MacEncryptUtils(MacEncryptUtils.H_MAC_SHA224, "1313".getBytes())
                .getDigest("123");
        System.out.println(s);
    }

    @Test
    public void test6() throws Exception {
        final String s = new MacEncryptUtils(MacEncryptUtils.H_MAC_SHA256, "1313".getBytes())
                .getDigest("123");
        System.out.println(s);
    }

    @Test
    public void test7() throws Exception {
        final String s = new MacEncryptUtils(MacEncryptUtils.H_MAC_SHA384, "1313".getBytes())
                .getDigest("123");
        System.out.println(s);
    }

    @Test
    public void test8() throws Exception {
        final String s = new MacEncryptUtils(MacEncryptUtils.H_MAC_SHA512, "1313".getBytes())
                .getDigest("123");
        System.out.println(s);
    }
}
