package io.github.jinlonghliao.commons.servlet.session.filter;

import io.github.jinlonghliao.commons.servlet.session.config.SessionConfig;
import io.github.jinlonghliao.commons.servlet.session.request.HttpServletRequest4SessionWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liaojinlong
 * @since 2020/10/9 15:10
 */
public class SessionFilter extends HttpFilter {
    private SessionConfig sessionConfig;

    public SessionFilter(SessionConfig sessionConfig) {
        this.sessionConfig = sessionConfig;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(new HttpServletRequest4SessionWrapper((HttpServletRequest) req, (HttpServletResponse) res, sessionConfig), res);

    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (!(req instanceof HttpServletRequest4SessionWrapper)) {
            chain.doFilter(new HttpServletRequest4SessionWrapper(req, res, sessionConfig), res);
        } else {
            chain.doFilter(req, res);
        }

    }
}
