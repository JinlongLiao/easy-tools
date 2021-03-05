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


/**
 * @author yonghaun
 * @since 1.0.0
 */
public interface APIParseable extends NoStoreableAPIParser {

    @Override
    APIDoc parseAndNotStore() throws Exception;

    /**
     * 解析接口并把结果已JSON格式写入文件。
     */
    void parse() throws Exception;

}