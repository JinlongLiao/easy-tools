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
