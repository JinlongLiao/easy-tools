package io.github.jinlonghliao.commons.crypt;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;

import java.security.Provider;
import java.security.Security;

public class ProviderTest {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    @Test
    public void testProviders() {
        System.out.println("-------当前JDK加密服务提供者-----");
        Provider[] pro = Security.getProviders();
        for (Provider p : pro) {
            System.out.println("Provider:" + p.getName() + " - version:" + p.getVersion());
            System.out.println(p.getInfo());
        }
        System.out.println();
        System.err.println("-------当前JDK支持的MessageDigest算法：");
        for (String s : Security.getAlgorithms("MessageDigest")) {
            System.out.println(s);
            System.out.println("-----------");
        }
        System.err.println("-------当前JDK支持的Cipher算法：");
        for (String s : Security.getAlgorithms("Cipher")) {
            System.out.println(s);
            System.out.println("-----------");

        }
        System.err.println("-------当前JDK支持的Mac算法：");
        for (String s : Security.getAlgorithms("Mac")) {
            System.out.println(s);
            System.out.println("-----------");
        }
        System.err.println("-------当前JDK支持的KeyStore算法：");
        for (String s : Security.getAlgorithms("KeyStore")) {
            System.out.println(s);
            System.out.println("-----------");
        }
        System.err.println("-------当前JDK支持的Signature算法：");
        for (String s : Security.getAlgorithms("Signature")) {
            System.out.println(s);
            System.out.println("-----------");
        }
        System.err.println("-------当前JDK支持的KeyPairGenerator算法：");
        for (String s : Security.getAlgorithms("KeyPairGenerator")) {
            System.out.println(s);
            System.out.println("-----------");
        }
    }
}
