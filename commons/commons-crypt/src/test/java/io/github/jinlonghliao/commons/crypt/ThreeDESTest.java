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
