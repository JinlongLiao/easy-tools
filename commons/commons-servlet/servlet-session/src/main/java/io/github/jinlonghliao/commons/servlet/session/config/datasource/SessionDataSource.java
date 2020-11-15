package io.github.jinlonghliao.commons.servlet.session.config.datasource;

/**
 * @author liaojinlong
 * @since 2020/10/12 17:48
 */
public class SessionDataSource {
    private String host;
    private Integer port;
    private String database;
    private String pwd;
    private Boolean userSsl;

    public SessionDataSource() {
    }

    public SessionDataSource(String host, Integer port, String database, String pwd, Boolean userSsl) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.pwd = pwd;
        this.userSsl = userSsl;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Boolean getUserSsl() {
        return userSsl;
    }

    public void setUserSsl(Boolean userSsl) {
        this.userSsl = userSsl;
    }
}
