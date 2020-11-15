package io.github.jinlonghliao.commons.crypt;

import io.github.jinlonghliao.common.hex.HexUtils;
import io.github.jinlonghliao.commons.crypt.digest.DigestUtils;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class Sm3Test {
    public static final String digest = "abc";

    @Test
    public void sm3() throws UnsupportedEncodingException {
        final DigestUtils sm3 = DigestUtils.SM3;
        final MessageDigest messageDigest = sm3.getMessageDigest();
        String sm3Str = sm3.getDigest(digest);
        System.out.println(sm3Str);
    }
}
