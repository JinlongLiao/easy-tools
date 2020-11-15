package io.github.jinlonghliao.commons.h2;

import io.github.jinlonghliao.commons.h2.db.DbWeb;
import org.junit.Test;

import java.io.IOException;

public class H2Test {
    @Test
    public void testWeb() throws IOException {
        final DbWeb.H2Db web = new DbWeb.H2Db();
        web.setPort(9999);
        web.setEnable(true);
        web.setAllowOther(true);
        final DbWeb.H2Db tcp = web.toClone();
        tcp.setEnable(true);
        tcp.setPort(9092);
        final DbWeb dbWeb = new DbWeb(tcp, web);
        new H2(dbWeb).startH2ServerMode();
        System.in.read();
    }
}
