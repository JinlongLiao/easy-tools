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
package io.github.jinlonghliao.commons.crypt.digest;

import io.github.jinlonghliao.common.charset.StdCharset;
import io.github.jinlonghliao.common.hex.HexUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * @author liaojinlong
 * @since 2020/9/29 14:14
 */
public class DigestUtils {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private static final Logger log = LoggerFactory.getLogger(DigestUtils.class);
    private static DigestUtils SHA;
    public static DigestUtils SHA_1;
    public static DigestUtils SHA_256;
    public static DigestUtils SHA_512;
    public static DigestUtils SHA3_256;
    public static DigestUtils SHA3_512;
    public static DigestUtils MD4;
    public static DigestUtils MD5;
    public static DigestUtils SM3;

    static {
        try {
            SHA = new DigestUtils("SHA");
            SHA_1 = new DigestUtils("SHA-1");
            SHA_256 = new DigestUtils("SHA-256");
            SHA_512 = new DigestUtils("SHA-512");
            SHA3_256 = new DigestUtils("SHA3-256");
            SHA3_512 = new DigestUtils("SHA3-512");
            MD4 = new DigestUtils("MD4");
            MD5 = new DigestUtils("MD5");
            SM3 = new DigestUtils("SM3");
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
        }
    }


    /**
     * @param digestName 摘要名称
     */
    private DigestUtils(String digestName) throws NoSuchAlgorithmException {
        messageDigest = MessageDigest.getInstance(digestName);
    }

    private MessageDigest messageDigest;

    /**
     * @return MessageDigest
     */
    public <T extends MessageDigest> T getMessageDigest() {
        return (T) messageDigest;
    }

    /**
     * 摘要
     *
     * @param str 加密后的报文
     * @return 摘要密文
     */
    public synchronized String getDigest(String str) throws UnsupportedEncodingException {
        return HexUtils.byteToHex(getByteDigest(str));
    }

    /**
     * 摘要
     *
     * @param str 加密后的报文
     * @return 摘要密文
     */
    public synchronized byte[] getByteDigest(String str) throws UnsupportedEncodingException {
        return getByteDigest(str.getBytes(StdCharset.CHARSET_UTF8));
    }

    /**
     * 摘要
     *
     * @param hex 加密后的报文
     * @return 摘要密文
     */
    public synchronized byte[] getByteDigest(byte[] hex) {
        messageDigest.update(hex);
        return messageDigest.digest();
    }
}

