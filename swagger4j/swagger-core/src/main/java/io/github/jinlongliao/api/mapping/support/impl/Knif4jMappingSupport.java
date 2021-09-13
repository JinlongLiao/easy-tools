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
package io.github.jinlongliao.api.mapping.support.impl;

import io.github.jinlongliao.api.mapping.support.MappingSupport;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;


/**
 * @author yonghuan
 * @since 2.2.0
 */
public class Knif4jMappingSupport extends MappingSupport {
    private final static String STATIC_RESCUE_PATH = "io/github/jinlongliao/knife4j/support/internal/";
    private final static String TEMPLATES_PATH = "templates/knife4j";
    private final static String[] resourcePattern = new String[]{
            "favicon-16x16.png"
            , "favicon-32x32.png"
            , "swagger-ui.js"
            , "sbuadmin.js"
            , "swagger-ui-bundle.js"
            , "swagger-ui-es-bundle.js"
            , "swagger-ui-es-bundle-core.js"
            , "swagger-ui-standalone-preset.js"
            , "index.html"
            , "oauth2-redirect.html"
            , "swagger-ui.css"
    };

    public Knif4jMappingSupport(ServletContext servletContext, String urlPatternMapping) throws IOException {
        super(servletContext, urlPatternMapping);
    }

    @Override
    public String[] getResourcePattern() {
        return resourcePattern;
    }

    @Override
    public String getTempPath() {
        return TEMPLATES_PATH;
    }

    @Override
    public String getResourcePath() {
        return STATIC_RESCUE_PATH;
    }


}
