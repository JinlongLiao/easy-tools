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
