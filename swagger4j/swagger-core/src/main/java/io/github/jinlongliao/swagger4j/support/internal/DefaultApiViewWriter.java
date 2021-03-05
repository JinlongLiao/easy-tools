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
package io.github.jinlongliao.swagger4j.support.internal;

import com.alibaba.fastjson.JSONWriter;
import io.github.jinlongliao.swagger4j.APIParseable;
import io.github.jinlongliao.swagger4j.APIParser;

import io.github.jinlongliao.swagger4j.ConfigResolver;
import io.github.jinlongliao.swagger4j.DefaultConfigResolver;
import io.github.jinlongliao.swagger4j.support.Constants;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author yonghuan
 * @since 1.2.0
 */
public class DefaultApiViewWriter implements ApiViewWriter {

    private final static Logger LOG = LoggerFactory.getLogger(DefaultApiViewWriter.class);
    private static AtomicBoolean scanfed = new AtomicBoolean();
    private ConfigResolver configResolver = new DefaultConfigResolver();

    protected String getTemplateName() {
        return "";
    }

    @Deprecated
    @Override
    public void writeIndex(HttpServletRequest request, HttpServletResponse response, String lang, Properties props)
            throws IOException {
    }

    @Override
    public void writeIndex(HttpServletRequest request, HttpServletResponse response, String lang) throws IOException {
        writeIndex(request, response, lang, configResolver.obtainConfig(request));
    }

    @Deprecated
    @Override
    public void writeApis(HttpServletRequest request, HttpServletResponse response, Properties props)
            throws Exception {
        String disabled = props.getProperty(Constants.DISABLED, "false");
        if (Boolean.TRUE.equals(Boolean.valueOf(disabled))) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        APIParseable restParser = APIParser.newInstance(props);
        response.setContentType("application/json;charset=utf-8");
        String devMode = props.getProperty("devMode");
        if (Boolean.valueOf(devMode)) {
            Object apis = restParser.parseAndNotStore();
            JSONWriter writer = new JSONWriter(response.getWriter());
            writer.writeObject(apis);
            writer.flush();
            writer.close();
        } else {
            if (!scanfed.get()) {
                restParser.parse();
                scanfed.set(true);
            }
            byte[] bs = Files.readAllBytes(Paths.get(props.getProperty("apiFile")));
            OutputStream out = response.getOutputStream();
            out.write(bs);
            out.flush();
            out.close();
        }
    }

    @Override
    public void writeApis(HttpServletRequest request, HttpServletResponse response) throws Exception {
        writeApis(request, response, configResolver.obtainConfig(request));
    }

    /**
     * @since 1.2.2
     */
    protected String buildResourcePath(HttpServletRequest request, Properties config) {
        String uri = request.getRequestURI();
        String suffix = (String) config.get("suffix");
        if (suffix != null) {
            int index = uri.lastIndexOf(suffix);
            if (index > 0) {
                uri = uri.substring(0, index);
            }
        }
        String path = uri.substring(uri.indexOf("statics") + 7);
        path = "com/cpjit/swagger4j/support/internal/statics" + path;
        return path;
    }

    @Deprecated
    protected String buildResourcePath(HttpServletRequest request) {
        return buildResourcePath(request, null);
    }

    @Override
    public void writeStatic(HttpServletRequest request, HttpServletResponse response, Properties props) throws IOException {
        String path = buildResourcePath(request, props);
        if (LOG.isDebugEnabled()) {
            LOG.debug("获取web资源文件：" + path);
        }
        String contentType = FileTypeMap.getContentType(path);
        response.setContentType(contentType);
        InputStream resource = DefaultApiViewWriter.class.getClassLoader().getResourceAsStream(path);
        if (resource == null) {
            response.sendError(404);
            return;
        }

        OutputStream out = null;
        try {
            out = response.getOutputStream();
            byte[] buff = new byte[512];
            int len;
            while ((len = resource.read(buff)) != -1) {
                out.write(buff, 0, len);
            }
            out.flush();
        } finally {
            IOUtils.closeQuietly(resource);
            IOUtils.closeQuietly(out);
        }
    }

    @Deprecated
    @Override
    public void writeStatic(HttpServletRequest request, HttpServletResponse response) throws IOException {
        writeStatic(request, response, configResolver.obtainConfig(request));
    }
}
