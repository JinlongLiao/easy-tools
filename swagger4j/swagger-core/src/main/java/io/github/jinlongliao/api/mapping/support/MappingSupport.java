package io.github.jinlongliao.api.mapping.support;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.github.jinlongliao.api.ConfigResolver;
import io.github.jinlongliao.api.DefaultConfigResolver;
import io.github.jinlongliao.api.mapping.filter.Swagger4jFilter;
import io.github.jinlongliao.api.mapping.support.impl.DelegateMappingSupport;
import io.github.jinlongliao.api.support.internal.ApiViewWriter;
import io.github.jinlongliao.api.support.internal.DefaultApiViewWriter;
import io.github.jinlongliao.api.support.internal.FileTypeMap;
import io.github.jinlongliao.api.util.ResourceUtil;
import io.github.jinlongliao.api.util.matcher.AntPathRequestMatcher;
import io.github.jinlongliao.api.util.matcher.HttpMethod;
import io.github.jinlongliao.api.util.matcher.RequestMatcher;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.*;

/**
 * 路径映射
 *
 * @author liaojinlong
 * @since 2021/9/10 16:07
 */
public abstract class MappingSupport {
    private static final Logger log = LoggerFactory.getLogger(MappingSupport.class);
    protected ServletContext servletContext;
    protected String urlPatternMapping;
    protected String contextPath;
    protected List<RequestMatcher> requestMatchers;
    protected String urlPrefix;
    protected Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
    protected ApiViewWriter apiViewWriter = new DefaultApiViewWriter();
    protected final static String JSON = "json";
    protected ConfigResolver configResolver = new DefaultConfigResolver();
    protected String v2Url;

    public MappingSupport() {
    }

    public MappingSupport(ServletContext servletContext, String urlPatternMapping) throws IOException {
        this.servletContext = servletContext;
        this.urlPatternMapping = urlPatternMapping;
        this.contextPath = servletContext.getContextPath();
        urlPrefix = contextPath + urlPatternMapping;
        urlPrefix = urlPrefix.replaceAll("/{2,}", "/");
        if (!"/".equals(urlPrefix) && urlPrefix.endsWith("/")) {
            urlPrefix = urlPrefix.substring(0, urlPrefix.length() - 1);
        }
        requestMatchers = new ArrayList<>(getResourcePattern().length);
        for (String resourcePattern : getResourcePattern()) {
            final String path = this.contextPath + "/" + urlPatternMapping + "/" + resourcePattern;
            final String pattern = path.replaceAll("/{2,}", "/");
            final AntPathRequestMatcher pathRequestMatcher = new AntPathRequestMatcher(pattern, HttpMethod.GET.name());
            requestMatchers.add(pathRequestMatcher);
        }


        // final Enumeration<URL> resources = AccessController.doPrivileged(new PrivilegedAction<Enumeration<URL>>() {
        //     @Override
        //     public Enumeration<URL> run() {
        //         try {
        //             return this.getClass().getClassLoader().getResources(getTempPath());
        //         } catch (IOException e) {
        //             log.error(e.getMessage(), e);
        //         }
        //         return null;
        //     }
        // });
        //
        // File path = null;
        // if (resources != null) {
        //     while (resources.hasMoreElements()) {
        //         final URL url = resources.nextElement();
        //          if (url.getFile().contains("jar")) {
        //             path = new File(url.getFile());
        //             break;
        //         }
        //     }
        // }
        configuration.setClassLoaderForTemplateLoading(this.getClass().getClassLoader(), getTempPath());
        // configuration.setDirectoryForTemplateLoading(path);
    }

    public abstract String[] getResourcePattern();

    public abstract String getTempPath();

    /**
     * 是否支持映射
     *
     * @param servletRequest
     * @param servletResponse
     * @return /
     * @throws IOException
     */
    public boolean doMapping(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        final Properties properties = configResolver.obtainConfig(request);
        if (v2Url == null) {
            final String basePath = properties.getProperty("basePath", "/api");
            final String apiFile = properties.getProperty("ApiFile", "/apiJson");
            v2Url = (request.getContextPath() + basePath + apiFile).replaceAll("//", "/");
        }

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
        } else if (uri.equals(v2Url)) {
            final String apiFile = properties.getProperty("apiFile");
            byte[] bs = Files.readAllBytes(Paths.get(apiFile));
            OutputStream out = response.getOutputStream();
            out.write(bs);
            out.flush();
            out.close();
            return true;
        }
        if (unsupportedRequest(request)) {
            return false;
        }

        String relativePath = request.getRequestURI().replaceFirst(urlPrefix, "");
        // 搜索静态资源
        String staticResource = getResourcePath() + "/" + relativePath;
        staticResource = staticResource.replaceAll("/{2,}", "/");
        InputStream is = ResourceUtil.getResourceAsStream(Swagger4jFilter.class, staticResource);
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
        String templateResource = getTempPath() + relativePath;
        templateResource = templateResource.replaceAll("/{2,}", "/");
        is = ResourceUtil.getResourceAsStream(templateResource);
        if (is != null) {
            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            try (Writer writer = response.getWriter()) {
                Map<String, Object> param = new HashMap<>(8);
                param.put("baseUrl", resolveBaseUrl(request));
                param.put("lang", "zh-cn");
                param.put("getApisUrl", resolveBaseUrl(request));
                for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                    param.put((String) entry.getKey(), entry.getValue());
                }
                String templateName = FilenameUtils.getName(relativePath);
                final Template template = configuration.getTemplate(templateName);
                try {
                    template.process(param, writer);
                } catch (TemplateException e) {
                    new ServletException(e);
                }
            }
            return true;
        }
        return false;
    }

    public abstract String getResourcePath();

    public void toIndex(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (Writer writer = response.getWriter()) {
            Map<String, Object> param = new HashMap<>(8);
            param.put("baseUrl", resolveBaseUrl(request));
            String lang = request.getParameter("lang");
            if (StringUtils.isBlank(lang)) {
                lang = "zh-cn";
            }
            Object enableSsl = request.getParameter("enableSsl");
            enableSsl = enableSsl == null || enableSsl.toString().isEmpty() ? false : true;
            param.put("enableSsl", enableSsl);
            param.put("lang", lang);
            param.put("getApisUrl", resolveBaseUrl(request));
            param.put("baseBath", this.urlPrefix);
            final Properties properties = configResolver.obtainConfig(request);
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                param.put(String.valueOf(entry.getKey()), entry.getValue());
            }
            String templateName = FilenameUtils.getName("index.html");
            final Template template = configuration.getTemplate(templateName);
            try {
                template.process(param, writer);
            } catch (TemplateException e) {
                new ServletException(e);
            }
        }
    }

    protected String resolveBaseUrl(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + this.urlPrefix;
    }

    protected boolean unsupportedRequest(HttpServletRequest request) {
        boolean result = true;
        for (RequestMatcher requestMatcher : requestMatchers) {
            final boolean matches = (result || requestMatcher.matches(request));
            if (matches) {
                return false;
            }
        }
        return true;
    }

    public static MappingSupport getDefaultMappingSupport(ServletContext servletContext, String urlPatternMapping) throws ServletException {
        try {
            return new DelegateMappingSupport(servletContext, urlPatternMapping);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ServletException(e);
        }
    }
}
