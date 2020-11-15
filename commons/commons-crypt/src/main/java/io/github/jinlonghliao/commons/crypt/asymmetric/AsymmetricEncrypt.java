package io.github.jinlonghliao.commons.crypt.asymmetric;


import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;


/**
 * 非对称加密算法工具类
 *
 * @author liaojinlong
 * @since 2020/9/29 22:53
 */


public interface AsymmetricEncrypt {
    /**
     * 生成密钥对
     *
     * @return KeyPair 密钥对
     */
      KeyPair getKeyPair() throws Exception;

    /**
     * 生成密钥
     *
     * @param password 生成密钥对的密码
     * @return
     * @throws Exception
     */
    KeyPair getKeyPair(String password) throws Exception;

    /**
     * 取得私钥
     *
     * @param keyPair 密钥对
     * @return byte[] 私钥
     */
    byte[] getPrivateKeyBytes(KeyPair keyPair);

    /**
     * 取得Base64编码的私钥
     *
     * @param keyPair 密钥对
     * @return String Base64编码的私钥
     */
    String getPrivateKey(KeyPair keyPair);

    /**
     * 取得公钥
     *
     * @param keyPair 密钥对
     * @return byte[] 公钥
     */
    byte[] getPublicKeyBytes(KeyPair keyPair);

    /**
     * 取得Base64编码的公钥
     *
     * @param keyPair 密钥对
     * @return String Base64编码的公钥
     */
    String getPublicKey(KeyPair keyPair);

    /**
     * 私钥加密
     *
     * @param data       待加密数据
     * @param privateKey 私钥字节数组
     * @return byte[] 加密数据
     */
    byte[] encryptByPrivateKey(byte[] data, byte[] privateKey) throws Exception;

    /**
     * 私钥加密
     *
     * @param data       待加密数据
     * @param privateKey Base64编码的私钥
     * @return String Base64编码的加密数据
     */
    String encryptByPrivateKey(String data, String privateKey) throws Exception;

    /**
     * 公钥加密
     *
     * @param data      待加密数据
     * @param publicKey 公钥字节数组
     * @return byte[] 加密数据
     */
    byte[] encryptByPublicKey(byte[] data, byte[] publicKey) throws Exception;

    /**
     * 公钥加密
     *
     * @param data      待加密数据
     * @param publicKey Base64编码的公钥
     * @return String Base64编码的加密数据
     */
    String encryptByPublicKey(String data, String publicKey) throws Exception;

    /**
     * 私钥解密
     *
     * @param data       待解密数据
     * @param privateKey 私钥字节数组
     * @return byte[] 解密数据
     */
    public byte[] decryptByPrivateKey(byte[] data, byte[] privateKey) throws Exception;

    /**
     * 私钥解密
     *
     * @param data       Base64编码的待解密数据
     * @param privateKey Base64编码的私钥
     * @return String 解密数据
     */
    public String decryptByPrivateKey(String data, String privateKey) throws Exception;

    /**
     * 公钥解密
     *
     * @param data      待解密数据
     * @param publicKey 公钥字节数组
     * @return byte[] 解密数据
     */
    public byte[] decryptByPublicKey(byte[] data, byte[] publicKey) throws Exception;

    /**
     * 公钥解密
     *
     * @param data      Base64编码的待解密数据
     * @param publicKey Base64编码的公钥
     * @return String 解密数据
     */
    String decryptByPublicKey(String data, String publicKey) throws Exception;

    /**
     * 生成校验码
     *
     * @param privateKey
     * @param data
     * @return 校验码
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws SignatureException
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     */
    String sign(String privateKey, byte[] data) throws NoSuchAlgorithmException,
            InvalidKeySpecException,
            SignatureException,
            InvalidKeyException, UnsupportedEncodingException;

    /**
     * 生成校验码
     *
     * @param privateKey
     * @param data
     * @return 校验码
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws SignatureException
     * @throws InvalidKeyException
     */
    String sign(byte[] privateKey, byte[] data) throws NoSuchAlgorithmException,
            InvalidKeySpecException,
            SignatureException,
            InvalidKeyException;

    /**
     * 生成校验码
     *
     * @param privateKey
     * @param data
     * @return 校验码
     * @throws SignatureException
     * @throws InvalidKeyException
     */
    String sign(PrivateKey privateKey, byte[] data) throws SignatureException, InvalidKeyException;

    /**
     * 校验码校验
     *
     * @param publicKey
     * @param data
     * @param signStr
     * @return /
     * @throws UnsupportedEncodingException
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    Boolean verify(String publicKey, byte[] data, String signStr) throws UnsupportedEncodingException,
            InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, SignatureException;

    /**
     * 校验码校验
     *
     * @param publicKey
     * @param data
     * @param signStr
     * @return /
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws SignatureException
     * @throws InvalidKeyException
     */
    Boolean verify(byte[] publicKey, byte[] data, String signStr) throws NoSuchAlgorithmException,
            InvalidKeySpecException,
            SignatureException,
            InvalidKeyException;

    /**
     * 校验码校验
     *
     * @param publicKey
     * @param data
     * @param signStr
     * @return /
     * @throws SignatureException
     * @throws InvalidKeyException
     */
    Boolean verify(PublicKey publicKey, byte[] data, String signStr) throws SignatureException, InvalidKeyException;


}