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
package io.github.jinlonghliao.common.core.text.csv;

import java.io.Serializable;

import io.github.jinlonghliao.common.core.util.CharUtil;

/**
 * CSV基础配置项
 *
 * @author looly
 * @since 4.0.5
 */
public class CsvConfig implements Serializable {
    private static final long serialVersionUID = -8069578249066158459L;

    /**
     * 字段分隔符，默认逗号','
     */
    protected char fieldSeparator = CharUtil.COMMA;
    /**
     * 文本分隔符，文本包装符，默认双引号'"'
     */
    protected char textDelimiter = CharUtil.DOUBLE_QUOTES;

    /**
     * 设置字段分隔符，默认逗号','
     *
     * @param fieldSeparator 字段分隔符，默认逗号','
     */
    public void setFieldSeparator(final char fieldSeparator) {
        this.fieldSeparator = fieldSeparator;
    }

    /**
     * 设置 文本分隔符，文本包装符，默认双引号'"'
     *
     * @param textDelimiter 文本分隔符，文本包装符，默认双引号'"'
     */
    public void setTextDelimiter(char textDelimiter) {
        this.textDelimiter = textDelimiter;
    }
}
