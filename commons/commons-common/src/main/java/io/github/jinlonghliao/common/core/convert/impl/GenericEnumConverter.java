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

import io.github.jinlonghliao.common.core.convert.AbstractConverter;

/**
 * 泛型枚举转换器
 * 
 * @param <E> 枚举类类型
 * @author Looly
 * @since 4.0.2
 * @deprecated 请使用{@link EnumConverter}
 */
@Deprecated
public class GenericEnumConverter<E extends Enum<E>> extends AbstractConverter<E> {
	private static final long serialVersionUID = 1L;

	private final Class<E> enumClass;
	
	/**
	 * 构造
	 * 
	 * @param enumClass 转换成的目标Enum类
	 */
	public GenericEnumConverter(Class<E> enumClass) {
		this.enumClass = enumClass;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected E convertInternal(Object value) {
		E enumValue = (E) EnumConverter.tryConvertEnum(value, this.enumClass);
		if(null == enumValue && false == value instanceof String){
			// 最后尝试valueOf转换
			enumValue = Enum.valueOf(this.enumClass, convertToStr(value));
		}
		return enumValue;
	}

	@Override
	public Class<E> getTargetType() {
		return this.enumClass;
	}
}
