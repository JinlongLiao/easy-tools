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
package io.github.jinlongliao.api.mapping.filter;

import io.github.jinlongliao.api.mapping.support.MappingSupport;
import io.github.jinlongliao.api.support.servlet.ApiServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;
import java.util.Collection;

/**
 * @author yonghuan
 * @see ApiServlet
 * @since 2.2.0
 */
public class Swagger4jFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(Swagger4jFilter.class);

    private MappingSupport mappingSupport;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String filterName = filterConfig.getFilterName();
        String urlPatternMapping;
        int majorVersion = filterConfig.getServletContext().getMajorVersion();
        if (majorVersion == 2) {
            urlPatternMapping = "/api/*";
        } else if ((majorVersion > 2)) {
            FilterRegistration filterRegistration = filterConfig.getServletContext().getFilterRegistration(filterName);
            Collection<String> urlPatternMappings = filterRegistration.getUrlPatternMappings();
            if (urlPatternMappings.size() != 1) {
                throw new IllegalArgumentException();
            }
            urlPatternMapping = urlPatternMappings.iterator().next();
        } else {
            throw new UnsupportedClassVersionError();
        }

        urlPatternMapping = urlPatternMapping.replaceAll("\\*", "");
        mappingSupport = MappingSupport.getDefaultMappingSupport(filterConfig.getServletContext(), urlPatternMapping);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        if (!mappingSupport.doMapping(servletRequest, servletResponse)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
