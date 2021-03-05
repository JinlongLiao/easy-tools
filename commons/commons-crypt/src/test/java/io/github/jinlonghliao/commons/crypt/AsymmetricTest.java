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

import io.github.jinlonghliao.common.charset.StdCharset;
import io.github.jinlonghliao.commons.crypt.asymmetric.AsymmetricEncryptUtils;
import org.junit.Test;

import java.security.KeyPair;

public class AsymmetricTest {

    @Test
    public void testDsa() throws Exception {
        final AsymmetricEncryptUtils encryptUtils = new AsymmetricEncryptUtils(AsymmetricEncryptUtils.DSA);
        //生成密钥对，一般生成之后可以放到配置文件中
        KeyPair keyPair = encryptUtils.getKeyPair();
        //公钥
        String publicKey = encryptUtils.getPublicKey(keyPair);
        //私钥
        String privateKey = encryptUtils.getPrivateKey(keyPair);

        System.out.println("公钥：\n" + publicKey);
        System.out.println("私钥：\n" + privateKey);

        String data = "DSA 加解密测试!";
        System.out.println("\n===========私钥签名==============");
        String sign = encryptUtils.sign(privateKey, data.getBytes(StdCharset.CHARSET_UTF8));
        System.out.println("签名后的数据:" + sign);
        Boolean verify = encryptUtils.verify(publicKey, data.getBytes(StdCharset.CHARSET_UTF8), sign);
        System.out.println("验签结果：:" + verify + "\n\n");

    }

    @Test
    public void testSm2() throws Exception {
        final AsymmetricEncryptUtils encryptUtils = new AsymmetricEncryptUtils(AsymmetricEncryptUtils.SM2);
        //生成密钥对，一般生成之后可以放到配置文件中
        KeyPair keyPair = encryptUtils.getKeyPair();
        //公钥
        String publicKey = encryptUtils.getPublicKey(keyPair);
        //私钥
        String privateKey = encryptUtils.getPrivateKey(keyPair);

        System.out.println("公钥：\n" + publicKey);
        System.out.println("私钥：\n" + privateKey);

        String data = "SM2 加解密测试!";

        System.out.println("\n===========公钥加密，私钥解密==============");
        String s1 = encryptUtils.encryptByPublicKey(data, publicKey);
        System.out.println("加密后的数据:" + s1);
        String s2 = encryptUtils.decryptByPrivateKey(s1, privateKey);
        System.out.println("解密后的数据:" + s2 + "\n\n");
        System.out.println("\n===========私钥签名==============");
        String sign = encryptUtils.sign(privateKey, data.getBytes(StdCharset.CHARSET_UTF8));
        System.out.println("签名后的数据:" + sign);
        Boolean verify = encryptUtils.verify(publicKey, data.getBytes(StdCharset.CHARSET_UTF8), sign);
        System.out.println("验签结果：:" + verify + "\n\n");

    }

    @Test
    public void testRSA() throws Exception {
        final AsymmetricEncryptUtils encryptUtils = new AsymmetricEncryptUtils(AsymmetricEncryptUtils.RSA);
        //生成密钥对，一般生成之后可以放到配置文件中
        KeyPair keyPair = encryptUtils.getKeyPair();
        //公钥
        String publicKey = encryptUtils.getPublicKey(keyPair);
        //私钥
        String privateKey = encryptUtils.getPrivateKey(keyPair);

        System.out.println("公钥：\n" + publicKey);
        System.out.println("私钥：\n" + privateKey);

        String data = "RSA 加解密测试!";
        {
            System.out.println("\n===========私钥加密，公钥解密==============");
            String s1 = encryptUtils.encryptByPrivateKey(data, privateKey);
            System.out.println("加密后的数据:" + s1);
            String s2 = encryptUtils.decryptByPublicKey(s1, publicKey);
            System.out.println("解密后的数据:" + s2 + "\n\n");
        }

        {
            System.out.println("\n===========公钥加密，私钥解密==============");
            String s1 = encryptUtils.encryptByPublicKey(data, publicKey);
            System.out.println("加密后的数据:" + s1);
            String s2 = encryptUtils.decryptByPrivateKey(s1, privateKey);
            System.out.println("解密后的数据:" + s2 + "\n\n");
        }

        {
            System.out.println("\n===========私钥签名==============");
            String sign = encryptUtils.sign(privateKey, data.getBytes(StdCharset.CHARSET_UTF8));
            System.out.println("签名后的数据:" + sign);
            Boolean verify = encryptUtils.verify(publicKey, data.getBytes(StdCharset.CHARSET_UTF8), sign);
            System.out.println("验签结果：:" + verify + "\n\n");
        }

    }
}
