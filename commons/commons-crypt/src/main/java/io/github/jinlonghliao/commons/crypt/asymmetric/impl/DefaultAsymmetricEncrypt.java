package io.github.jinlonghliao.commons.crypt.asymmetric.impl;

import io.github.jinlonghliao.common.charset.StdCharset;
import io.github.jinlonghliao.common.hex.HexUtils;
import io.github.jinlonghliao.commons.crypt.asymmetric.AsymmetricEncrypt;
import io.github.jinlonghliao.commons.crypt.asymmetric.AsymmetricEncryptUtils;
import org.bouncycastle.crypto.prng.FixedSecureRandom;

import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Objects;

/**
 * @author liaojinlong
 * @since 2020/9/30 13:45
 */
public class DefaultAsymmetricEncrypt implements AsymmetricEncrypt {
    /**
     * 非对称密钥算法
     */
    protected String algorithm;
    /**
     * 密钥长度，在512到65536位之间，建议不要太长，否则速度很慢，生成的加密数据很长
     */
    protected int keySize;
    protected Signature signature;
    protected String provider;
    protected String keyAlgorithm;
    protected KeyPair keyPair;

    public DefaultAsymmetricEncrypt(AsymmetricEncryptUtils.Builder builder) throws NoSuchAlgorithmException {
        this.keyAlgorithm = builder.getKeyAlgorithm();
        this.algorithm = builder.getAlgorithm();
        this.keySize = builder.getKeySize();
        this.provider = builder.getProvider();
        this.signature = Signature.getInstance(builder.getSignature());
    }

    /**
     * 生成密钥对
     *
     * @return KeyPair 密钥对
     */
    @Override
    public KeyPair getKeyPair() throws Exception {
        return keyPair = getKeyPair(null);
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

        final KeyPairGenerator keyPairGenerator = getKeyPairGenerator();
        //初始化密钥生成器
        if (password == null) {
            keyPairGenerator.initialize(keySize);
        } else {
            SecureRandom secureRandom = FixedSecureRandom.getInstance(password);
            secureRandom.setSeed(password.getBytes(StdCharset.CHARSET_UTF8));
            keyPairGenerator.initialize(keySize, secureRandom);
        }
        //生成密钥对
        return keyPair = keyPairGenerator.generateKeyPair();
    }

    /**
     * 取得私钥
     *
     * @param keyPair 密钥对
     * @return byte[] 私钥
     */
    @Override
    public byte[] getPrivateKeyBytes(KeyPair keyPair) {
        return keyPair.getPrivate().getEncoded();
    }

    /**
     * 取得Base64编码的私钥
     *
     * @param keyPair 密钥对
     * @return String Base64编码的私钥
     */
    @Override
    public String getPrivateKey(KeyPair keyPair) {
        return Base64.getEncoder().encodeToString(getPrivateKeyBytes(keyPair));
    }

    /**
     * 取得公钥
     *
     * @param keyPair 密钥对
     * @return byte[] 公钥
     */
    @Override
    public byte[] getPublicKeyBytes(KeyPair keyPair) {
        return keyPair.getPublic().getEncoded();
    }

    /**
     * 取得Base64编码的公钥
     *
     * @param keyPair 密钥对
     * @return String Base64编码的公钥
     */
    @Override
    public String getPublicKey(KeyPair keyPair) {
        return Base64.getEncoder().encodeToString(getPublicKeyBytes(keyPair));
    }

    /**
     * 私钥加密
     *
     * @param data       待加密数据
     * @param privateKey 私钥字节数组
     * @return byte[] 加密数据
     */
    @Override
    public byte[] encryptByPrivateKey(byte[] data, byte[] privateKey) throws Exception {
        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(keyAlgorithm);
        //生成私钥
        PrivateKey key = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKey));
        //数据加密
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        return cipher.doFinal(data);
    }

    /**
     * 私钥加密
     *
     * @param data       待加密数据
     * @param privateKey Base64编码的私钥
     * @return String Base64编码的加密数据
     */
    @Override
    public String encryptByPrivateKey(String data, String privateKey) throws Exception {
        byte[] key = Base64.getDecoder().decode(privateKey);
        return Base64.getEncoder().encodeToString(encryptByPrivateKey(data.getBytes(StdCharset.CHARSET_UTF8), key));
    }

    /**
     * 公钥加密
     *
     * @param data      待加密数据
     * @param publicKey 公钥字节数组
     * @return byte[] 加密数据
     */
    @Override
    public byte[] encryptByPublicKey(byte[] data, byte[] publicKey) throws Exception {
        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(keyAlgorithm);
        //生成公钥
        PublicKey key = keyFactory.generatePublic(new X509EncodedKeySpec(publicKey));
        //数据加密
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    /**
     * 公钥加密
     *
     * @param data      待加密数据
     * @param publicKey Base64编码的公钥
     * @return String Base64编码的加密数据
     */
    @Override
    public String encryptByPublicKey(String data, String publicKey) throws Exception {
        byte[] key = Base64.getDecoder().decode(publicKey);
        return Base64.getEncoder().encodeToString(encryptByPublicKey(data.getBytes(StdCharset.CHARSET_UTF8), key));
    }

    /**
     * 私钥解密
     *
     * @param data       待解密数据
     * @param privateKey 私钥字节数组
     * @return byte[] 解密数据
     */
    @Override
    public byte[] decryptByPrivateKey(byte[] data, byte[] privateKey) throws Exception {
        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(keyAlgorithm);
        //生成私钥
        PrivateKey key = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKey));
        //数据解密
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    /**
     * 私钥解密
     *
     * @param data       Base64编码的待解密数据
     * @param privateKey Base64编码的私钥
     * @return String 解密数据
     */
    @Override
    public String decryptByPrivateKey(String data, String privateKey) throws Exception {
        byte[] key = Base64.getDecoder().decode(privateKey);
        return new String(decryptByPrivateKey(Base64.getDecoder().decode(data), key), StdCharset.CHARSET_UTF8);
    }

    /**
     * 公钥解密
     *
     * @param data      待解密数据
     * @param publicKey 公钥字节数组
     * @return byte[] 解密数据
     */
    @Override
    public byte[] decryptByPublicKey(byte[] data, byte[] publicKey) throws Exception {
        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        //产生公钥
        PublicKey key = keyFactory.generatePublic(new X509EncodedKeySpec(publicKey));
        //数据解密
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    /**
     * 公钥解密
     *
     * @param data      Base64编码的待解密数据
     * @param publicKey Base64编码的公钥
     * @return String 解密数据
     */
    @Override
    public String decryptByPublicKey(String data, String publicKey) throws Exception {
        byte[] key = Base64.getDecoder().decode(publicKey);
        return new String(decryptByPublicKey(Base64.getDecoder().decode(data), key), StdCharset.CHARSET_UTF8);
    }

    @Override
    public String sign(String privateKey, byte[] data) throws NoSuchAlgorithmException,
            InvalidKeySpecException,
            SignatureException,
            InvalidKeyException, UnsupportedEncodingException {
        return this.sign(Base64.getDecoder().decode(privateKey.getBytes(StdCharset.CHARSET_UTF8)), data);
    }

    @Override
    public String sign(byte[] privateKey, byte[] data) throws NoSuchAlgorithmException,
            InvalidKeySpecException,
            SignatureException,
            InvalidKeyException {
        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(keyAlgorithm);
        //生成私钥
        PrivateKey key = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKey));
        return this.sign(key, data);
    }

    @Override
    public String sign(PrivateKey privateKey, byte[] data) throws SignatureException, InvalidKeyException {

        //初始化私钥，签名只能是私钥
        signature.initSign(privateKey);
        //更新签名数据
        signature.update(data);
        //签名，返回签名后的字节数组
        byte[] b = signature.sign();
        return HexUtils.bytes2Hex(b);
    }

    @Override
    public Boolean verify(String publicKey, byte[] data, String signStr) throws UnsupportedEncodingException, InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        return this.verify(Base64.getDecoder().decode(publicKey.getBytes(StdCharset.CHARSET_UTF8)), data, signStr);
    }

    @Override
    public Boolean verify(byte[] publicKey, byte[] data, String signStr) throws NoSuchAlgorithmException,
            InvalidKeySpecException,
            SignatureException,
            InvalidKeyException {
        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(keyAlgorithm);
        //生成公钥
        PublicKey key = keyFactory.generatePublic(new X509EncodedKeySpec(publicKey));
        return this.verify(key, data, signStr);
    }

    @Override
    public Boolean verify(PublicKey publicKey, byte[] data, String signStr) throws SignatureException, InvalidKeyException {
        //验证
        //初始化公钥，验证只能是公钥
        signature.initVerify(publicKey);
        //更新验证的数据
        signature.update(data);
        //签名和验证一致返回true  不一致返回false
        return signature.verify(HexUtils.hex2Bytes(signStr));
    }

    /**
     * KeyPairGenerator
     *
     * @return KeyPairGenerator
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     */
    protected KeyPairGenerator getKeyPairGenerator() throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyPairGenerator keyPairGenerator;
        if (Objects.isNull(provider)) {
            keyPairGenerator = KeyPairGenerator.getInstance(this.keyAlgorithm);
        } else {
            keyPairGenerator = KeyPairGenerator.getInstance(this.keyAlgorithm, this.provider);
        }
        return keyPairGenerator;
    }
}
