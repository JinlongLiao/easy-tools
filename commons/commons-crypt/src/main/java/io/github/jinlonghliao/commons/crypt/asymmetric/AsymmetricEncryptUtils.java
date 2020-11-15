package io.github.jinlonghliao.commons.crypt.asymmetric;


import io.github.jinlonghliao.commons.crypt.asymmetric.impl.DefaultAsymmetricEncrypt;
import io.github.jinlonghliao.commons.crypt.asymmetric.impl.Sm2AsymmetricEncrypt;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Objects;


/**
 * 非对称加密算法工具类
 *
 * @author liaojinlong
 * @since 2020/9/29 22:53
 */


public class AsymmetricEncryptUtils implements AsymmetricEncrypt {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * 建议重写 keySize
     */
    public static final Builder RSA = new Builder("RSA", "SHA256WITHRSA", 1024);
    /**
     * 不支持加解密
     */
    public static final Builder DSA = new Builder("DSA", "DSA", 1024);
    /**
     * 不支持公钥加解密
     */
    public static final Builder SM2 = new Builder("SM2", "EC",
            "SM3WITHSM2", "BC", Sm2AsymmetricEncrypt.class, 1024);
    private final AsymmetricEncrypt asymmetricEncrypt;

    public AsymmetricEncryptUtils(AsymmetricEncryptUtils.Builder builder) throws NoSuchAlgorithmException {
        final Class<? extends AsymmetricEncrypt> aClass = builder.getaClass();
        if (Objects.isNull(aClass)) {
            asymmetricEncrypt = new DefaultAsymmetricEncrypt(builder);
        } else {
            try {
                final Constructor<? extends AsymmetricEncrypt> constructor = aClass.getConstructor(Builder.class);
                constructor.setAccessible(true);
                asymmetricEncrypt = constructor.newInstance(builder);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public KeyPair getKeyPair() throws Exception {
        return asymmetricEncrypt.getKeyPair();
    }

    @Override
    public KeyPair getKeyPair(String password) throws Exception {
        return asymmetricEncrypt.getKeyPair(password);
    }

    @Override
    public byte[] getPrivateKeyBytes(KeyPair keyPair) {
        return asymmetricEncrypt.getPrivateKeyBytes(keyPair);
    }

    @Override
    public String getPrivateKey(KeyPair keyPair) {
        return asymmetricEncrypt.getPrivateKey(keyPair);
    }

    @Override
    public byte[] getPublicKeyBytes(KeyPair keyPair) {
        return asymmetricEncrypt.getPublicKeyBytes(keyPair);
    }

    @Override
    public String getPublicKey(KeyPair keyPair) {
        return asymmetricEncrypt.getPublicKey(keyPair);
    }

    @Override
    public byte[] encryptByPrivateKey(byte[] data, byte[] privateKey) throws Exception {
        return asymmetricEncrypt.encryptByPrivateKey(data, privateKey);
    }

    @Override
    public String encryptByPrivateKey(String data, String privateKey) throws Exception {
        return asymmetricEncrypt.encryptByPrivateKey(data, privateKey);
    }

    @Override
    public byte[] encryptByPublicKey(byte[] data, byte[] publicKey) throws Exception {
        return asymmetricEncrypt.encryptByPublicKey(data, publicKey);
    }

    @Override
    public String encryptByPublicKey(String data, String publicKey) throws Exception {
        return asymmetricEncrypt.encryptByPublicKey(data, publicKey);
    }

    @Override
    public byte[] decryptByPrivateKey(byte[] data, byte[] privateKey) throws Exception {
        return asymmetricEncrypt.decryptByPrivateKey(data, privateKey);
    }

    @Override
    public String decryptByPrivateKey(String data, String privateKey) throws Exception {
        return asymmetricEncrypt.decryptByPrivateKey(data, privateKey);
    }

    @Override
    public byte[] decryptByPublicKey(byte[] data, byte[] publicKey) throws Exception {
        return asymmetricEncrypt.decryptByPublicKey(data, publicKey);
    }

    @Override
    public String decryptByPublicKey(String data, String publicKey) throws Exception {
        return asymmetricEncrypt.decryptByPublicKey(data, publicKey);
    }

    @Override
    public String sign(String privateKey, byte[] data) throws NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, InvalidKeyException, UnsupportedEncodingException {
        return asymmetricEncrypt.sign(privateKey, data);
    }

    @Override
    public String sign(byte[] privateKey, byte[] data) throws NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, InvalidKeyException {
        return asymmetricEncrypt.sign(privateKey, data);
    }

    @Override
    public String sign(PrivateKey privateKey, byte[] data) throws SignatureException, InvalidKeyException {
        return asymmetricEncrypt.sign(privateKey, data);
    }

    @Override
    public Boolean verify(String publicKey, byte[] data, String signStr) throws UnsupportedEncodingException, InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        return asymmetricEncrypt.verify(publicKey, data, signStr);
    }

    @Override
    public Boolean verify(byte[] publicKey, byte[] data, String signStr) throws NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, InvalidKeyException {
        return asymmetricEncrypt.verify(publicKey, data, signStr);
    }

    @Override
    public Boolean verify(PublicKey publicKey, byte[] data, String signStr) throws SignatureException, InvalidKeyException {
        return asymmetricEncrypt.verify(publicKey, data, signStr);
    }


    public static class Builder {
        private final Class<? extends AsymmetricEncrypt> aClass;

        private final String algorithm;
        private final String signature;
        private final String provider;
        private final Integer keySize;
        private final String keyAlgorithm;

        public Builder(String algorithm, String signature, Integer keySize) {
            this(algorithm, signature, null, keySize);
        }

        public Builder(String algorithm, String signature, String provider, Integer keySize) {
            this(algorithm, algorithm, signature, null, keySize);

        }

        public Builder(String algorithm, String keyAlgorithm, String signature, String provider, Integer keySize) {
            this(algorithm, keyAlgorithm, signature, provider, null, keySize);
        }

        public Builder(String algorithm,
                       String keyAlgorithm,
                       String signature,
                       String provider,
                       Class<? extends AsymmetricEncrypt> aClass,
                       Integer keySize) {
            this.keyAlgorithm = keyAlgorithm;
            this.algorithm = algorithm;
            this.aClass = aClass;
            this.keySize = keySize;
            this.provider = provider;
            this.signature = signature;
        }

        public Class<? extends AsymmetricEncrypt> getaClass() {
            return aClass;
        }

        public String getAlgorithm() {
            return algorithm;
        }

        public String getSignature() {
            return signature;
        }

        public String getProvider() {
            return provider;
        }

        public Integer getKeySize() {
            return keySize;
        }

        public String getKeyAlgorithm() {
            return keyAlgorithm;
        }
    }

}