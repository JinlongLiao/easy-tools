package io.github.jinlonghliao.commons.servlet.session.request;

import io.github.jinlonghliao.commons.servlet.session.config.SessionConfig;
import io.github.jinlonghliao.commons.servlet.session.datasource.SessionDatasourceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author liaojinlong
 * @since 2020/10/9 15:02
 */
public class HttpServletRequest4SessionWrapper extends HttpServletRequestWrapper {
    protected HttpServletRequest httpServletRequest;
    protected HttpServletResponse httpServletResponse;
    protected SessionConfig sessionConfig;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request             the {@link HttpServletRequest} to be wrapped.
     * @param httpServletResponse
     * @param sessionConfig
     * @throws IllegalArgumentException if the request is null
     */
    public HttpServletRequest4SessionWrapper(HttpServletRequest request,
                                             HttpServletResponse httpServletResponse,
                                             SessionConfig sessionConfig) {
        super(request);
        this.httpServletRequest = request;
        this.httpServletResponse = httpServletResponse;
        this.sessionConfig = sessionConfig;
    }


    @Override
    public HttpSession getSession(boolean create) {
        return SessionDatasourceFactory.getInstance(sessionConfig)
                .getSessionDatasource()
                .getHttpSession(httpServletRequest, create);
    }

    @Override
    public HttpSession getSession() {
        return this.getSession(Boolean.TRUE);
    }

}
