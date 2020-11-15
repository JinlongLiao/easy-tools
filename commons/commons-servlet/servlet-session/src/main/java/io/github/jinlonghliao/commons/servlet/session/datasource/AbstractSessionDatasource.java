package io.github.jinlonghliao.commons.servlet.session.datasource;

import io.github.jinlonghliao.commons.servlet.session.HttpSessionImp;
import io.github.jinlonghliao.commons.servlet.session.config.SessionConfig;
import io.github.jinlonghliao.commons.servlet.session.config.key.SessionKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @author liaojinlong
 * @since 2020/10/9 21:31
 */

public abstract class AbstractSessionDatasource implements SessionDatasource {
    protected SessionConfig sessionConfig;
    protected HttpSession httpSession;

    @Override
    public void setSessionConfig(SessionConfig sessionConfig) {
        this.sessionConfig = sessionConfig;
    }

    @Override
    public HttpSession getHttpSession(HttpServletRequest httpServletRequest, boolean create) {
        HttpSession session = httpServletRequest.getSession(create);
        if (session != null) {
            session = new HttpSessionImp(session, this);
        }
        this.httpSession = session;
        return session;
    }


}
