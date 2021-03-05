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
package io.github.jinlonghliao.commons.crypt.asymmetric.impl;

import io.github.jinlonghliao.common.charset.StdCharset;
import io.github.jinlonghliao.commons.crypt.asymmetric.AsymmetricEncryptUtils;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.prng.FixedSecureRandom;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.*;

/**
 * @author liaojinlong
 * @since 2020/9/30 14:03
 */
public class Sm2AsymmetricEncrypt extends DefaultAsymmetricEncrypt {
    private static final Logger log = LoggerFactory.getLogger(Sm2AsymmetricEncrypt.class);

    public Sm2AsymmetricEncrypt(AsymmetricEncryptUtils.Builder builder) throws NoSuchAlgorithmException {
        super(builder);
    }

    /**
     * 生成密钥对
     *
     * @param password 生成密钥对的密码
     * @return
     * @throws Exception
     */
    @Override
    public KeyPair getKeyPair(String password) throws Exception {
        BigInteger SM2_ECC_P = new BigInteger("8542D69E4C044F18E8B92435BF6FF7DE457283915C45517D722EDB8B08F1DFC3", 16);
        BigInteger SM2_ECC_A = new BigInteger("787968B4FA32C3FD2417842E73BBFEFF2F3C848B6831D7E0EC65228B3937E498", 16);
        BigInteger SM2_ECC_B = new BigInteger("63E4C6D3B23B0C849CF84241484BFE48F61D59A5B16BA06E6E12D1DA27C5249A", 16);
        BigInteger SM2_ECC_N = new BigInteger("8542D69E4C044F18E8B92435BF6FF7DD297720630485628D5AE74EE7C32E79B7", 16);
        BigInteger SM2_ECC_H = ECConstants.ONE;
        BigInteger SM2_ECC_GX = new BigInteger("421DEBD61B62EAB6746434EBC3CC315E32220B3BADD50BDC4C4E6C147FEDD43D", 16);
        BigInteger SM2_ECC_GY = new BigInteger("0680512BCBB42C07D47349D2153B70C4E5D7FDFCBFA36EA1A85841B9E46E09A2", 16);

        ECCurve curve = new ECCurve.Fp(SM2_ECC_P, SM2_ECC_A, SM2_ECC_B, SM2_ECC_N, SM2_ECC_H);

        ECPoint g = curve.createPoint(SM2_ECC_GX, SM2_ECC_GY);
        ECDomainParameters domainParams = new ECDomainParameters(curve, g, SM2_ECC_N);

        KeyPairGenerator keyPairGenerator = getKeyPairGenerator();

        ECParameterSpec aKeyGenParams = new ECParameterSpec(domainParams.getCurve(), domainParams.getG(), domainParams.getN(), domainParams.getH());

        keyPairGenerator.initialize(aKeyGenParams);

        //初始化密钥生成器
        if (password == null) {
            keyPairGenerator.initialize(aKeyGenParams);
        } else {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes(StdCharset.CHARSET_UTF8));
            keyPairGenerator.initialize(aKeyGenParams, secureRandom);
        }
        //生成密钥对
        return keyPair = keyPairGenerator.generateKeyPair();
    }
}
