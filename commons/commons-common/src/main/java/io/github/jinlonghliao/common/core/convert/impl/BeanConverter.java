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

import io.github.jinlonghliao.common.core.bean.BeanUtil;
import io.github.jinlonghliao.common.core.bean.copier.BeanCopier;
import io.github.jinlonghliao.common.core.bean.copier.CopyOptions;
import io.github.jinlonghliao.common.core.bean.copier.ValueProvider;
import io.github.jinlonghliao.common.core.convert.AbstractConverter;
import io.github.jinlonghliao.common.core.convert.ConvertException;
import io.github.jinlonghliao.common.core.map.MapProxy;
import io.github.jinlonghliao.common.core.util.ObjectUtil;
import io.github.jinlonghliao.common.core.util.ReflectUtil;
import io.github.jinlonghliao.common.core.util.TypeUtil;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Bean转换器，支持：
 * <pre>
 * Map =》 Bean
 * Bean =》 Bean
 * ValueProvider =》 Bean
 * </pre>
 * 
 * @param <T> Bean类型
 * @author Looly
 * @since 4.0.2
 */
public class BeanConverter<T> extends AbstractConverter<T> {
	private static final long serialVersionUID = 1L;

	private final Type beanType;
	private final Class<T> beanClass;
	private final CopyOptions copyOptions;
	
	/**
	 * 构造，默认转换选项，注入失败的字段忽略
	 * 
	 * @param beanType 转换成的目标Bean类型
	 */
	public BeanConverter(Type beanType) {
		this(beanType, CopyOptions.create().setIgnoreError(true));
	}

	/**
	 * 构造，默认转换选项，注入失败的字段忽略
	 * 
	 * @param beanClass 转换成的目标Bean类
	 */
	public BeanConverter(Class<T> beanClass) {
		this(beanClass, CopyOptions.create().setIgnoreError(true));
	}
	
	/**
	 * 构造
	 * 
	 * @param beanType 转换成的目标Bean类
	 * @param copyOptions Bean转换选项参数
	 */
	@SuppressWarnings("unchecked")
	public BeanConverter(Type beanType, CopyOptions copyOptions) {
		this.beanType = beanType;
		this.beanClass = (Class<T>) TypeUtil.getClass(beanType);
		this.copyOptions = copyOptions;
	}

	@Override
	protected T convertInternal(Object value) {
		if(value instanceof Map ||
				value instanceof ValueProvider ||
				BeanUtil.isBean(value.getClass())) {
			if(value instanceof Map && this.beanClass.isInterface()) {
				// 将Map动态代理为Bean
				return MapProxy.create((Map<?, ?>)value).toProxyBean(this.beanClass);
			}

			//限定被转换对象类型
			return BeanCopier.create(value, ReflectUtil.newInstanceIfPossible(this.beanClass), this.beanType, this.copyOptions).copy();
		} else if(value instanceof byte[]){
			// 尝试反序列化
			return ObjectUtil.deserialize((byte[])value);
		}

		throw new ConvertException("Unsupported source type: {}", value.getClass());
	}

	@Override
	public Class<T> getTargetType() {
		return this.beanClass;
	}
}
