package io.github.jinlonghliao.commons.servlet.session.config.key;

import java.util.List;

/**
 * @author liaojinlong
 * @since 2020/10/9 17:38
 */
public class SessionKey {
    private String dataSourceName;
    private String appName;
    private String ip;
    private Integer port;

    public SessionKey(String dataSourceName, String appName, String ip, Integer port) {
        this.dataSourceName = dataSourceName;
        this.appName = appName;
        this.ip = ip;
        this.port = port;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}

