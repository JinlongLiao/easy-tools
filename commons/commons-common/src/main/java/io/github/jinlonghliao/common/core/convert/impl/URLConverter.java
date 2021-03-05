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
package io.github.jinlonghliao.common.core.convert.impl;

import java.io.File;
import java.net.URI;
import java.net.URL;

import io.github.jinlonghliao.common.core.convert.AbstractConverter;

/**
 * URL对象转换器
 * @author Looly
 *
 */
public class URLConverter extends AbstractConverter<URL>{
	private static final long serialVersionUID = 1L;

	@Override
	protected URL convertInternal(Object value) {
		try {
			if(value instanceof File){
				return ((File)value).toURI().toURL();
			}
			
			if(value instanceof URI){
				return ((URI)value).toURL();
			}
			return new URL(convertToStr(value));
		} catch (Exception e) {
			// Ignore Exception
		}
		return null;
	}

}
