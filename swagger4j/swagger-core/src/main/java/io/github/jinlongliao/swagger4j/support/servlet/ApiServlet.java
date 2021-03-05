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
package io.github.jinlongliao.swagger4j.support.servlet;

import io.github.jinlongliao.swagger4j.SwaggerMappingSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * @author yonghuan
 * @see io.github.jinlongliao.swagger4j.SwaggerFilter
 * @since 1.0.0
 */
public class ApiServlet extends HttpServlet {

    private SwaggerMappingSupport mappingSupport;

    @Override
    public void init() throws ServletException {

        ServletConfig servletConfig = getServletConfig();
        ServletContext servletContext = servletConfig.getServletContext();
        int majorVersion = servletContext.getMajorVersion();
        String servletMapping;
        if (majorVersion == 2) {
            servletMapping = "/api/*";
        } else if ((majorVersion > 2)) {
            Collection<String> servletMappings = servletContext.getServletRegistration(servletConfig.getServletName()).getMappings();
            if (servletMappings.size() != 1) {
                throw new IllegalArgumentException();
            }
            servletMapping = servletMappings.iterator().next();
        } else {
            throw new UnsupportedClassVersionError();
        }

        servletMapping = servletMapping.replaceAll("\\*", "");
        mappingSupport = new SwaggerMappingSupport(servletContext, servletMapping);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!mappingSupport.doMapping(req, resp)) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
