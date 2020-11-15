package io.github.jinlonghliao.commons.crypt.cert;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;

/**
 * @author liaojinlong
 * @since 2020/9/29 13:36
 */
public class CertUtil {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static CertificateParse getX509Cert(String cert) throws Exception {
        return new CertificateParse(cert);
    }
}
