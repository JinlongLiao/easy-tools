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
package io.github.jinlongliao.swagger4j.support.springmvc;

import io.github.jinlongliao.swagger4j.support.Constants;
import io.github.jinlongliao.swagger4j.support.internal.ApiViewWriter;
import io.github.jinlongliao.swagger4j.support.internal.DefaultApiViewWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yonghuan
 * @since 1.0.0
 * @deprecated 从2.2.0开始被废弃，请使用 {@link io.github.jinlongliao.swagger4j.SwaggerFilter} 替代
 */
@Deprecated
@Controller
@RequestMapping("/api")
public class ApiController implements InitializingBean, Constants {

    private ApiViewWriter apiViewWriter = new DefaultApiViewWriter();

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public void index(HttpServletRequest request, HttpServletResponse response, @RequestParam(defaultValue = DEFAULT_LANG) String lang) throws Exception {
        apiViewWriter.writeIndex(request, response, lang);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public void queryApi(HttpServletRequest request, HttpServletResponse response) throws Exception {
        apiViewWriter.writeApis(request, response);
    }

    /**
     * @since 1.2.0
     */
    @RequestMapping(value = "statics/**", method = RequestMethod.GET)
    public void queryStatic(HttpServletRequest request, HttpServletResponse response) throws Exception {
        apiViewWriter.writeStatic(request, response);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }

}
