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
package io.github.jinlonghliao.common.core.lang.intern;

import io.github.jinlonghliao.common.core.lang.SimpleCache;

/**
 * 使用WeakHashMap(线程安全)存储对象的规范化对象，注意此对象需单例使用！<br>
 *
 * @author looly
 * @since 5.4.3
 */
public class WeakInterner<T> implements Interner<T>{

	private final SimpleCache<T, T> cache = new SimpleCache<>();

	@Override
	public T intern(T sample) {
		if(null == sample){
			return null;
		}
		return cache.get(sample, ()->sample);
	}
}
