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
package io.github.jinlongliao.swagger4j;

import io.github.jinlongliao.swagger4j.support.internal.ApiViewWriter;
import io.github.jinlongliao.swagger4j.support.internal.DefaultApiViewWriter;
import io.github.jinlongliao.swagger4j.support.internal.FileTypeMap;
import io.github.jinlongliao.swagger4j.util.ResourceUtil;
import io.github.jinlongliao.swagger4j.util.matcher.AntPathRequestMatcher;
import io.github.jinlongliao.swagger4j.util.matcher.HttpMethod;
import io.github.jinlongliao.swagger4j.util.matcher.RequestMatcher;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author yonghuan
 * @since 2.2.0
 */
public class SwaggerMappingSupport {
    private final static String[] RESOURCE_PATTERNS = {
            "favicon-16x16.png"
            , "favicon-32x32.png"
            , "swagger-ui.js"
            , "swagger-ui-bundle.js"
            , "swagger-ui-es-bundle.js"
            , "swagger-ui-es-bundle-core.js"
            , "swagger-ui-standalone-preset.js"
            , "index.html"
            , "oauth2-redirect.html"
            , "swagger-ui.css"
    };
    private final static String STATIC_RESCUE_PATH = "io/github/jinlongliao/swagger4j/support/internal/statics";
    private final static String TEMPLATES_PATH = "templates/swagger4j";
    private String contextPath;
    private List<RequestMatcher> requestMatchers;
    private String urlPrefix;
    private ITemplateEngine templateEngine;
    private ApiViewWriter apiViewWriter = new DefaultApiViewWriter();
    private final static String JSON = "json";
    private final ConfigResolver configResolver = new DefaultConfigResolver();

    public SwaggerMappingSupport(ServletContext servletContext, String urlPatternMapping) throws ServletException {
        this.contextPath = servletContext.getContextPath();
        urlPrefix = contextPath + urlPatternMapping;
        urlPrefix = urlPrefix.replaceAll("/{2,}", "/");
        if (!"/".equals(urlPrefix) && urlPrefix.endsWith("/")) {
            urlPrefix = urlPrefix.substring(0, urlPrefix.length() - 1);
        }
        final List<String> strings = Arrays.asList(RESOURCE_PATTERNS);
        requestMatchers = new ArrayList<>(RESOURCE_PATTERNS.length);
        for (String resourcePattern : RESOURCE_PATTERNS) {
            final String path = this.contextPath + "/" + urlPatternMapping + "/" + resourcePattern;
            final String pattern = path.replaceAll("/{2,}", "/");
            final AntPathRequestMatcher pathRequestMatcher = new AntPathRequestMatcher(pattern, HttpMethod.GET.name());
            requestMatchers.add(pathRequestMatcher);
        }
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver(SwaggerFilter.class.getClassLoader());
        templateResolver.setPrefix(TEMPLATES_PATH + "/");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        this.templateEngine = templateEngine;
    }

    public boolean doMapping(ServletRequest servletRequest, ServletResponse servletResponse)
            throws IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        if (uri.equals(this.urlPrefix)) {
            String accept = request.getHeader("accept");
            if (accept != null && accept.contains(JSON)) {
                try {
                    apiViewWriter.writeApis(request, response);
                } catch (Exception e) {
                    throw new IOException(e);
                }
            } else {
                toIndex(request, response);
            }
            return true;
        }
        if (unsupportedRequest(request)) {
            return false;
        }

        String relativePath = request.getRequestURI().replaceFirst(urlPrefix, "");
        // 搜索静态资源
        String staticResource = STATIC_RESCUE_PATH + "/" + relativePath;
        staticResource = staticResource.replaceAll("/{2,}", "/");
        InputStream is = ResourceUtil.getResourceAsStream(SwaggerFilter.class, staticResource);
        OutputStream out;
        if (is != null) {
            String contentType = FileTypeMap.getContentType(staticResource);
            response.setContentType(contentType);
            out = response.getOutputStream();
            IOUtils.copy(is, out);
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(out);
            return true;
        }

        // 搜索模板
        String templateResource = TEMPLATES_PATH + relativePath;
        templateResource = templateResource.replaceAll("/{2,}", "/");
        is = ResourceUtil.getResourceAsStream(templateResource);
        if (is != null) {
            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            try (Writer writer = response.getWriter()) {
                WebContext webContext = new WebContext(request, response, request.getServletContext());
                webContext.setVariable("baseUrl", resolveBaseUrl(request));
                webContext.setVariable("lang", "zh-cn");
                webContext.setVariable("getApisUrl", resolveBaseUrl(request));
                final Properties properties = configResolver.obtainConfig(request);
                for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                    webContext.setVariable((String) entry.getKey(), entry.getValue());
                }
                String templateName = FilenameUtils.getName(relativePath);
                templateEngine.process(templateName, webContext, writer);
            }
            return true;
        }
        return false;
    }

    private void toIndex(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (Writer writer = response.getWriter()) {
            WebContext webContext = new WebContext(request, response, request.getSession().getServletContext());
            webContext.setVariable("baseUrl", resolveBaseUrl(request));
            String lang = request.getParameter("lang");
            if (StringUtils.isBlank(lang)) {
                lang = "zh-cn";
            }
            Object enableSsl = request.getParameter("enableSsl");
            enableSsl = enableSsl == null || enableSsl.toString().isEmpty() ? false : true;
            webContext.setVariable("enableSsl", enableSsl);
            webContext.setVariable("lang", lang);
            webContext.setVariable("getApisUrl", resolveBaseUrl(request));
            final Properties properties = configResolver.obtainConfig(request);
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                webContext.setVariable(String.valueOf(entry.getKey()), entry.getValue());
            }
            String templateName = FilenameUtils.getName("index.html");
            templateEngine.process(templateName, webContext, writer);
        }
    }

    private String resolveBaseUrl(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + this.urlPrefix;
    }

    private boolean unsupportedRequest(HttpServletRequest request) {
        boolean result = true;
        for (RequestMatcher requestMatcher : requestMatchers) {
            final boolean matches = (result || requestMatcher.matches(request));
            if (matches) {
                return false;
            }
        }
        return result;
    }
}
