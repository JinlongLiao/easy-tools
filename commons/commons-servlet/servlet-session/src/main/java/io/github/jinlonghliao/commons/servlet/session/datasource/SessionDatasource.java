package io.github.jinlonghliao.commons.servlet.session.datasource;

import io.github.jinlonghliao.commons.servlet.session.config.SessionConfig;
import io.github.jinlonghliao.commons.servlet.session.config.key.SessionKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * @author liaojinlong
 * @since 2020/10/9 16:32
 */
public interface SessionDatasource {
    /**
     * 设置 Session 唯一标识
     *
     * @param sessionConfig
     */
    void setSessionConfig(SessionConfig sessionConfig);


    /**
     * 获取 自定义的 HttpSession
     *
     * @param httpServletRequest
     * @param create
     * @return HttpSession
     */
    HttpSession getHttpSession(HttpServletRequest httpServletRequest, boolean create);

    /**
     * 获取唯一标识
     *
     * @return 标识
     */
    default String getId() {
        return this.getClass().getName();
    }

    long getCreationTime();


    long getLastAccessedTime();

    void setMaxInactiveInterval(int interval);


    int getMaxInactiveInterval();


    Object getAttribute(String name);


    Enumeration<String> getAttributeNames();


    void setAttribute(String name, Object value);


    void removeAttribute(String name);


    void invalidate();


    boolean isNew();
}
