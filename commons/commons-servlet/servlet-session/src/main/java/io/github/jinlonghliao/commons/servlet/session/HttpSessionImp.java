package io.github.jinlonghliao.commons.servlet.session;

import io.github.jinlonghliao.common.collection.CollectionUtils;
import io.github.jinlonghliao.common.core.collection.CollectionUtil;
import io.github.jinlonghliao.commons.servlet.session.datasource.SessionDatasource;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liaojinlong
 * @since 2020/10/9 18:44
 */
public class HttpSessionImp implements HttpSession {
    private final SessionDatasource sessionDatasource;
    private final HttpSession httpSession;
    private final static Map<String, HttpSession> httpSessionMap = new ConcurrentHashMap<>();
    private static final HttpSessionContext httpSessionContextImp = new HttpSessionContextImp();

    public HttpSessionImp(HttpSession httpSession, SessionDatasource sessionDatasource) {
        this.httpSession = httpSession;
        this.sessionDatasource = sessionDatasource;
        HttpSessionImp.httpSessionMap.put(httpSession.getId(), this);
    }

    @Override
    public long getCreationTime() {
        return this.sessionDatasource.getCreationTime();
    }

    @Override
    public String getId() {
        return httpSession.getId();
    }

    @Override
    public long getLastAccessedTime() {
        return this.sessionDatasource.getLastAccessedTime();
    }

    @Override
    public ServletContext getServletContext() {
        return httpSession.getServletContext();
    }


    @Override
    public void setMaxInactiveInterval(int interval) {
        this.sessionDatasource.setMaxInactiveInterval(interval);
    }

    @Override
    public int getMaxInactiveInterval() {
        return this.sessionDatasource.getMaxInactiveInterval();
    }

    @Override
    public HttpSessionContext getSessionContext() {
        return httpSessionContextImp;
    }

    @Override
    public Object getAttribute(String name) {
        return this.sessionDatasource.getAttribute(name);
    }

    @Override
    public Object getValue(String name) {
        return this.getAttribute(name);
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return this.sessionDatasource.getAttributeNames();
    }

    @Override
    public String[] getValueNames() {
        return CollectionUtils.newArrayList(CollectionUtil.asIterator(this.getAttributeNames())).toArray(new String[0]);
    }

    @Override
    public void setAttribute(String name, Object value) {
        this.sessionDatasource.setAttribute(name, value);
    }

    @Override
    public void putValue(String name, Object value) {
        this.setAttribute(name, value);
    }

    @Override
    public void removeAttribute(String name) {
        this.sessionDatasource.removeAttribute(name);
    }

    @Override
    public void removeValue(String name) {
        this.removeAttribute(name);
    }

    @Override
    public void invalidate() {
        this.sessionDatasource.invalidate();
    }

    @Override
    public boolean isNew() {
        return this.sessionDatasource.isNew();
    }

    /**
     * @author liaojinlong
     * @since 2020/10/9 18:24
     */
    static class HttpSessionContextImp implements javax.servlet.http.HttpSessionContext {
        @Override
        public HttpSession getSession(String sessionId) {
            return HttpSessionImp.httpSessionMap.get(sessionId);
        }

        @Override
        public Enumeration<String> getIds() {
            return Collections.enumeration(HttpSessionImp.httpSessionMap.keySet());
        }
    }
}
