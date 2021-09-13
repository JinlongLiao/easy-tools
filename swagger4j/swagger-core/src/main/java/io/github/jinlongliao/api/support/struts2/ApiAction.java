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
package io.github.jinlongliao.api.support.struts2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.jinlongliao.api.support.Constants;
import io.github.jinlongliao.api.support.internal.ApiViewWriter;
import io.github.jinlongliao.api.support.internal.Struts2ApiViewWriter;
import io.github.jinlongliao.api.util.ResourceUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Inject;

/**
 * @author yonghuan
 * @since 1.0.0
 * @deprecated 从2.2.0开始被废弃，请使用 {@link io.github.jinlongliao.api.SwaggerFilter} 替代
 */
@Deprecated
@SuppressWarnings("serial")
public class ApiAction extends ActionSupport implements Constants {

    @Inject("struts.action.extension")
    private String actionExtension;

    @Inject("struts.devMode")
    private String devMode;

    private ApiViewWriter apiViewWriter = new Struts2ApiViewWriter();

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        Properties props = loadSettings(request);
        apiViewWriter.writeApis(request, ServletActionContext.getResponse(), props);
        return null;
    }


    @Deprecated
    public void toIndex() throws Exception {
        index();
    }

    /**
     * @throws Exception
     * @since 1.2.0
     */
    public void index() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        String lang = request.getParameter("lang");
        if (StringUtils.isBlank(lang)) {
            lang = DEFAULT_LANG;
        }
        Properties props = loadSettings(request);
        apiViewWriter.writeIndex(request, ServletActionContext.getResponse(), lang, props);
    }

    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @since 1.2.0
     */
    public void statics() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        apiViewWriter.writeStatic(request, response, loadSettings(request));
    }

    private Properties loadSettings(HttpServletRequest request) throws IOException {
        Properties props = new Properties();
        InputStream is = ResourceUtil.getResourceAsStream("swagger.properties");
        props.load(is);
        String path = request.getContextPath();
        String host = request.getServerName() + ":" + request.getServerPort() + path;
        props.setProperty("apiHost", host);
        String apiFile = props.getProperty("apiFile");
        if (StringUtils.isBlank(apiFile)) {
            apiFile = DEFAULT_API_FILE;
        }
        String apiFilePath = request.getServletContext().getRealPath(apiFile);
        // 相对
        props.setProperty("ApiFile", apiFile);
        props.setProperty("apiFile", apiFilePath);
        if (StringUtils.isBlank(props.getProperty("devMode"))) {
            props.setProperty("devMode", devMode);
        }
        String suffix = props.getProperty("suffix");
        if (StringUtils.isBlank(suffix)) {
            suffix = "";
        }
        props.put("suffix", suffix);
        return props;
    }
}
