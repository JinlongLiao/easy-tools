/**
 * Copyright 2020-2021 JinlongLiao
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
