package io.github.jinlonghliao.commons.h2.db;


import java.util.Objects;

/**
 * @author LiaoJL
 * @description TODO
 * @file DbWeb.java
 * @CopyRight (C) http://www.koal.com/
 * @email jinlongliao@foxmail.com
 * @date 2020/3/14 20:49
 */
public class DbWeb {
    private H2Db tcp;
    private H2Db web;

    public DbWeb(H2Db tcp, H2Db web) {
        this.tcp = tcp;
        this.web = web;
    }

    public H2Db getTcp() {
        return tcp;
    }

    public void setTcp(H2Db tcp) {
        this.tcp = tcp;
    }

    public H2Db getWeb() {
        return web;
    }

    public void setWeb(H2Db web) {
        this.web = web;
    }

    public static class H2Db {
        private boolean enable;
        private int port;
        private boolean allowOther;

        public boolean isEnable() {
            return enable;
        }

        public boolean isAllowOther() {
            return allowOther;
        }

        public void setAllowOther(boolean allowOther) {
            this.allowOther = allowOther;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }


        public H2Db toClone() {
            final H2Db h2Db = new H2Db();
            h2Db.setAllowOther(this.allowOther);
            h2Db.setEnable(this.enable);
            h2Db.setPort(this.port);
            return h2Db;
        }
    }
}
