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

import io.github.jinlonghliao.common.hex.HexUtils;
import io.github.jinlonghliao.commons.crypt.symmetry.SymmetryEncryptUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ThreeDESTest {
    public static String plainText = "中华人民共和国万岁，世界人民大团结";
    private static final byte[] HEX_KEY = HexUtils.hex2Bytes("64EC7C763AB7BF64");
    private SymmetryEncryptUtils encryptUtils;
    private String encode;

    @Before
    public void init() {
        System.out.println("原文：" + plainText);
    }

    @Test
    public void test() throws Exception {
        encryptUtils = new SymmetryEncryptUtils(SymmetryEncryptUtils.DES_CBP, HEX_KEY);
        encode = encryptUtils.encode(plainText);
    }

    @Test
    public void test1() throws Exception {
        encryptUtils = new SymmetryEncryptUtils(SymmetryEncryptUtils.DES, HEX_KEY);
        encode = encryptUtils.encode(plainText);
    }

    @Test
    public void test2() throws Exception {
        encryptUtils = new SymmetryEncryptUtils(SymmetryEncryptUtils.DES_CBP, HEX_KEY);
        encode = encryptUtils.encode(plainText);
    }


    @After
    public void destroy() throws Exception {
        System.out.println(encode);
        System.out.println(encryptUtils.decode(encode));
    }
}
