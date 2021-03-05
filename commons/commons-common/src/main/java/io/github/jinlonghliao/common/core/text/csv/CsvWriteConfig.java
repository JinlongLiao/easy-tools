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
 * CSV写出配置项
 * 
 * @author looly
 *
 */
public class CsvWriteConfig extends CsvConfig implements Serializable {
	private static final long serialVersionUID = 5396453565371560052L;

	/** 是否始终使用文本分隔符，文本包装符，默认false，按需添加 */
	protected boolean alwaysDelimitText;
	/** 换行符 */
	protected char[] lineDelimiter = {CharUtil.CR, CharUtil.LF};

	/**
	 * 默认配置
	 * 
	 * @return 默认配置
	 */
	public static CsvWriteConfig defaultConfig() {
		return new CsvWriteConfig();
	}

	/**
	 * 设置是否始终使用文本分隔符，文本包装符，默认false，按需添加
	 * 
	 * @param alwaysDelimitText 是否始终使用文本分隔符，文本包装符，默认false，按需添加
	 */
	public void setAlwaysDelimitText(boolean alwaysDelimitText) {
		this.alwaysDelimitText = alwaysDelimitText;
	}
	
	/**
	 * 设置换行符
	 * 
	 * @param lineDelimiter 换行符
	 */
	public void setLineDelimiter(char[] lineDelimiter) {
		this.lineDelimiter = lineDelimiter;
	}
}
