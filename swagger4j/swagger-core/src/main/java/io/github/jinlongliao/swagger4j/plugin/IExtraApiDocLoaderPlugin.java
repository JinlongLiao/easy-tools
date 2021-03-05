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
package io.github.jinlongliao.swagger4j.plugin;

import io.github.jinlongliao.swagger4j.APIDoc;

import java.util.Properties;

/**
 * 额外插件
 *
 * @author liaojinlong
 * @since 2021/1/3 00:27
 */
public interface IExtraApiDocLoaderPlugin {
    /**
     * 额外插件，用于自定义
     *
     * @param apiDoc
     * @param properties
     * @return /
     */
    APIDoc extraPlugin(APIDoc apiDoc, Properties properties);
}
