package io.github.jinlongliao.api.mapping.support.impl;

import io.github.jinlongliao.api.ConfigResolver;
import io.github.jinlongliao.api.mapping.support.MappingSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

/**
 * 委托
 *
 * @author liaojinlong
 * @since 2021/9/13 11:44
 */
public class DelegateMappingSupport extends MappingSupport {
    private static final Logger log = LoggerFactory.getLogger(DelegateMappingSupport.class);
    private MappingSupport mappingSupport;

    public DelegateMappingSupport(ServletContext servletContext, String urlPatternMapping) throws IOException {
        this.servletContext = servletContext;
        this.urlPatternMapping = urlPatternMapping;
        initDelegate(this.configResolver);
    }

    private void initDelegate(ConfigResolver configResolver) {
        try {
            final Properties properties = configResolver.obtainConfig(null);
            final String uiType = properties.getProperty("uiType", "");
            if ("knife".equalsIgnoreCase(uiType)) {
                mappingSupport = new Knif4jMappingSupport(servletContext, urlPatternMapping);
            } else {
                mappingSupport = new SwaggerMappingSupport(servletContext, urlPatternMapping);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public String[] getResourcePattern() {
        return this.mappingSupport.getResourcePattern();
    }

    @Override
    public boolean doMapping(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        return this.mappingSupport.doMapping(servletRequest, servletResponse);
    }

    @Override
    public void toIndex(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.mappingSupport.toIndex(request, response);
    }

    @Override
    protected String resolveBaseUrl(HttpServletRequest request) {
        return super.resolveBaseUrl(request);
    }

    @Override
    protected boolean unsupportedRequest(HttpServletRequest request) {
        return super.unsupportedRequest(request);
    }

    @Override
    public String getTempPath() {
        return this.mappingSupport.getTempPath();
    }

    @Override
    public String getResourcePath() {
        return this.mappingSupport.getResourcePath();
    }
}
