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
package io.github.jinlonghliao.common.core.bean;

import io.github.jinlonghliao.common.core.lang.SimpleCache;
import io.github.jinlonghliao.common.core.lang.func.Func0;

/**
 * Bean属性缓存<br>
 * 缓存用于防止多次反射造成的性能问题
 * @author Looly
 *
 */
public enum BeanDescCache {
	INSTANCE;
	
	private final SimpleCache<Class<?>, BeanDesc> bdCache = new SimpleCache<>();
	
	/**
	 * 获得属性名和{@link BeanDesc}Map映射
	 * @param beanClass Bean的类
	 * @param supplier 对象不存在时创建对象的函数
	 * @return 属性名和{@link BeanDesc}映射
	 * @since 5.4.2
	 */
	public BeanDesc getBeanDesc(Class<?> beanClass, Func0<BeanDesc> supplier){
		return bdCache.get(beanClass, supplier);
	}
}
