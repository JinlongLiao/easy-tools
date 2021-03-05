/*
 * Copyright 2020-2021.
 * 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
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
