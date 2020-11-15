package io.github.jinlonghliao.commons.crypt;

import io.github.jinlonghliao.commons.crypt.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class Md5Test {
    @Test
    public void test() throws UnsupportedEncodingException {
        final String s = DigestUtils.MD5.getDigest("123");
        System.out.println(s);
        Assert.assertEquals("EQ", s.length(), 32);

    }
}
