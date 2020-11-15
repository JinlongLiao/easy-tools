package io.github.jinlonghliao.commons.crypt.symmetry;

import io.github.jinlonghliao.common.charset.StdCharset;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.Security;
import java.util.Arrays;
import java.util.Base64;

/**
 * @author liaojinlong
 * @since 2020/9/29 16:35
 */
public class SymmetryEncryptUtils {
    public static class Builder {
        private final String algorithm;
        private final String algorithmName;

        public Builder(String algorithm, String algorithmName) {
            this.algorithm = algorithm;
            this.algorithmName = algorithmName;
        }
    }

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private final String algorithm;
    private final String algorithmName;

    private Cipher enCipher;
    private Cipher deCipher;
    public static final Builder SM4 = new Builder("SM4", "SM4");
    public static final Builder SM4_CBP = new Builder("SM4", "SM4/CBC/PKCS5Padding");
    public static final Builder DES = new Builder("DES", "DES");
    public static final Builder DES_CBP = new Builder("DES", "DES/CBC/PKCS5Padding");
    public static final Builder AES = new Builder("AES", "AES");
    public static final Builder AES_CBP = new Builder("AES", "AES/CBC/PKCS5Padding");


    public SymmetryEncryptUtils(Builder builder, byte[] hexKey) throws Exception {
        this.algorithm = builder.algorithm;
        this.algorithmName = builder.algorithmName;
        init(hexKey);
    }

    private void init(byte[] hexKey) throws Exception {
        deCipher = Cipher.getInstance(algorithmName);
        enCipher = Cipher.getInstance(algorithmName);
        Key sm4En = new SecretKeySpec(hexKey, algorithm);
        Key sm4Den = new SecretKeySpec(hexKey, algorithm);
        enCipher.init(Cipher.ENCRYPT_MODE, sm4En, new IvParameterSpec(new byte[hexKey.length]));
        deCipher.init(Cipher.DECRYPT_MODE, sm4Den, new IvParameterSpec(new byte[hexKey.length]));
    }

    public String encode(String paramStr) throws Exception {
        String cipherText = null;

        byte[] srcData = paramStr.getBytes(StdCharset.CHARSET_UTF8);
        byte[] cipherArray = encode(srcData);
        cipherText = Base64.getEncoder().encodeToString(cipherArray);
        return cipherText;
    }

    public byte[] encode(byte[] data) throws Exception {
        return enCipher.doFinal(data);
    }

    public String decode(String cipherText) throws Exception {
        String decryptStr = "";
        byte[] cipherData = Base64.getDecoder().decode(cipherText.getBytes(StdCharset.CHARSET_UTF8));
        byte[] srcData = decode(cipherData);
        decryptStr = new String(srcData, StdCharset.CHARSET_UTF8);
        return decryptStr;
    }

    public byte[] decode(byte[] cipherText) throws Exception {
        return deCipher.doFinal(cipherText);
    }

    public boolean verify(String cipherText, String paramStr) throws Exception {
        boolean flag;
        byte[] cipherData = Base64.getDecoder().decode(cipherText);
        byte[] decryptData = decode(cipherData);
        byte[] srcData = paramStr.getBytes(StdCharset.CHARSET_UTF8);
        flag = Arrays.equals(decryptData, srcData);
        return flag;
    }
}
