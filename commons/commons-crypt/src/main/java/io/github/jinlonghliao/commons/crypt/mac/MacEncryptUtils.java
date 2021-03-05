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
package io.github.jinlonghliao.commons.crypt.mac;

import io.github.jinlonghliao.common.hex.HexUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;

/**
 * @author liaojinlong
 * @since 2020/9/29 14:14
 */
public class MacEncryptUtils {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private String algorithm;

    public static class Builder {
        private final String algorithm;

        public Builder(String algorithm) {
            this.algorithm = algorithm;
        }
    }

    private static final Logger log = LoggerFactory.getLogger(MacEncryptUtils.class);
    public static Builder H_MAC_MD2 = new Builder("HmacMD2");
    public static Builder H_MAC_MD4 = new Builder("HmacMD4");
    public static Builder H_MAC_MD5 = new Builder("HmacMD5");
    public static Builder H_MAC_SHA1 = new Builder("HmacSHA1");
    public static Builder H_MAC_SHA224 = new Builder("HmacSHA224");
    public static Builder H_MAC_SHA256 = new Builder("HmacSHA256");
    public static Builder H_MAC_SHA384 = new Builder("HmacSHA384");
    public static Builder H_MAC_SHA512 = new Builder("HmacSHA512");
    private final Mac mac;

    public MacEncryptUtils(Builder builder, byte[] hexKey) throws Exception {
        SecretKey restoreSecretKey = new SecretKeySpec(hexKey, builder.algorithm);
        //实例化MAC
        mac = Mac.getInstance(restoreSecretKey.getAlgorithm());
        //初始化Mac
        mac.init(restoreSecretKey);
    }

    /**
     * @return MessageDigest
     */
    public <T extends Mac> T getMac() {
        return (T) mac;
    }

    /**
     * 摘要
     *
     * @param str 加密后的报文
     * @return 摘要密文
     */
    public synchronized String getDigest(String str) {
        return HexUtils.bytes2Hex(mac.doFinal());
    }
}

