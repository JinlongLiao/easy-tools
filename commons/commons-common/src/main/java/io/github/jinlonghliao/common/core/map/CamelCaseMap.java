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
package io.github.jinlonghliao.common.core.map;

import java.util.HashMap;
import java.util.Map;

import io.github.jinlonghliao.common.core.util.StrUtil;

/**
 * 驼峰Key风格的Map<br>
 * 对KEY转换为驼峰，get("int_value")和get("intValue")获得的值相同，put进入的值也会被覆盖
 * 
 * @author Looly
 *
 * @param <K> 键类型
 * @param <V> 值类型
 * @since 4.0.7
 */
public class CamelCaseMap<K, V> extends CustomKeyMap<K, V> {
	private static final long serialVersionUID = 4043263744224569870L;

	// ------------------------------------------------------------------------- Constructor start
	/**
	 * 构造
	 */
	public CamelCaseMap() {
		this(DEFAULT_INITIAL_CAPACITY);
	}

	/**
	 * 构造
	 * 
	 * @param initialCapacity 初始大小
	 */
	public CamelCaseMap(int initialCapacity) {
		this(initialCapacity, DEFAULT_LOAD_FACTOR);
	}

	/**
	 * 构造
	 * 
	 * @param m Map
	 */
	public CamelCaseMap(Map<? extends K, ? extends V> m) {
		this(DEFAULT_LOAD_FACTOR, m);
	}

	/**
	 * 构造
	 * 
	 * @param loadFactor 加载因子
	 * @param m Map
	 */
	public CamelCaseMap(float loadFactor, Map<? extends K, ? extends V> m) {
		this(m.size(), loadFactor);
		this.putAll(m);
	}

	/**
	 * 构造
	 * 
	 * @param initialCapacity 初始大小
	 * @param loadFactor 加载因子
	 */
	public CamelCaseMap(int initialCapacity, float loadFactor) {
		super(new HashMap<>(initialCapacity, loadFactor));
	}
	// ------------------------------------------------------------------------- Constructor end

	/**
	 * 将Key转为驼峰风格，如果key为字符串的话
	 * 
	 * @param key KEY
	 * @return 驼峰Key
	 */
	@Override
	protected Object customKey(Object key) {
		if (key instanceof CharSequence) {
			key = StrUtil.toCamelCase(key.toString());
		}
		return key;
	}
}
