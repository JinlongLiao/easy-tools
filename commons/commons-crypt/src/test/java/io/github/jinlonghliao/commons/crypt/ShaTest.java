package io.github.jinlonghliao.commons.crypt;

import io.github.jinlonghliao.commons.crypt.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class ShaTest {
    @Test
    public void test() throws UnsupportedEncodingException {
        final String s = DigestUtils.SHA_256.getDigest("123");
        System.out.println(s);
        Assert.assertEquals("EQ", s.length(), 64);
    }
}
