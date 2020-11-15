package io.github.jinlonghliao.commons.servlet.session.config;

import io.github.jinlonghliao.commons.servlet.session.config.datasource.SessionDataSource;
import io.github.jinlonghliao.commons.servlet.session.config.key.SessionKey;

/**
 * Session存储源 配置
 *
 * @author liaojinlong
 * @since 2020/10/12 17:51
 */
public class SessionConfig {
    private SessionKey sessionKey;
    private SessionDataSource sessionDataSource;

    public SessionConfig(SessionKey sessionKey, SessionDataSource sessionDataSource) {
        this.sessionKey = sessionKey;
        this.sessionDataSource = sessionDataSource;
    }

    public SessionKey getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(SessionKey sessionKey) {
        this.sessionKey = sessionKey;
    }

    public SessionDataSource getSessionDataSource() {
        return sessionDataSource;
    }

    public void setSessionDataSource(SessionDataSource sessionDataSource) {
        this.sessionDataSource = sessionDataSource;
    }
}
