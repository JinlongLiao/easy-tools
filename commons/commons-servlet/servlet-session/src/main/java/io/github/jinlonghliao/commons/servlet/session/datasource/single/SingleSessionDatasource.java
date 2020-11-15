package io.github.jinlonghliao.commons.servlet.session.datasource.single;

import io.github.jinlonghliao.commons.servlet.session.datasource.AbstractSessionDatasource;

import java.util.Enumeration;

/**
 * @author liaojinlong
 * @since 2020/10/10 13:27
 */
public class SingleSessionDatasource extends AbstractSessionDatasource {
    @Override
    public long getCreationTime() {
        return httpSession.getCreationTime();
    }

    @Override
    public long getLastAccessedTime() {
        return httpSession.getLastAccessedTime();
    }

    @Override
    public void setMaxInactiveInterval(int interval) {
        httpSession.setMaxInactiveInterval(interval);
    }

    @Override
    public int getMaxInactiveInterval() {
        return httpSession.getMaxInactiveInterval();
    }

    @Override
    public Object getAttribute(String name) {
        return httpSession.getAttribute(name);
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return httpSession.getAttributeNames();
    }

    @Override
    public void setAttribute(String name, Object value) {
        httpSession.setAttribute(name, value);
    }

    @Override
    public void removeAttribute(String name) {
        httpSession.removeAttribute(name);
    }

    @Override
    public void invalidate() {
        httpSession.invalidate();
    }

    @Override
    public boolean isNew() {
        return httpSession.isNew();
    }
}
