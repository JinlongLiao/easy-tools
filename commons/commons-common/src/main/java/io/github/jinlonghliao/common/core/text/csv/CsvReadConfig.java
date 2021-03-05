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

/**
 * CSV读取配置项
 * 
 * @author looly
 *
 */
public class CsvReadConfig extends CsvConfig implements Serializable {
	private static final long serialVersionUID = 5396453565371560052L;

	/** 是否首行做为标题行，默认false */
	protected boolean containsHeader;
	/** 是否跳过空白行，默认true */
	protected boolean skipEmptyRows = true;
	/** 每行字段个数不同时是否抛出异常，默认false */
	protected boolean errorOnDifferentFieldCount;

	/**
	 * 默认配置
	 * 
	 * @return 默认配置
	 */
	public static CsvReadConfig defaultConfig() {
		return new CsvReadConfig();
	}

	/**
	 * 设置是否首行做为标题行，默认false
	 * 
	 * @param containsHeader 是否首行做为标题行，默认false
	 */
	public void setContainsHeader(boolean containsHeader) {
		this.containsHeader = containsHeader;
	}

	/**
	 * 设置是否跳过空白行，默认true
	 * 
	 * @param skipEmptyRows 是否跳过空白行，默认true
	 */
	public void setSkipEmptyRows(boolean skipEmptyRows) {
		this.skipEmptyRows = skipEmptyRows;
	}

	/**
	 * 设置每行字段个数不同时是否抛出异常，默认false
	 * 
	 * @param errorOnDifferentFieldCount 每行字段个数不同时是否抛出异常，默认false
	 */
	public void setErrorOnDifferentFieldCount(boolean errorOnDifferentFieldCount) {
		this.errorOnDifferentFieldCount = errorOnDifferentFieldCount;
	}
}
