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

/**
 * 忽略大小写的Map<br>
 * 对KEY忽略大小写，get("Value")和get("value")获得的值相同，put进入的值也会被覆盖
 * 
 * @author Looly
 *
 * @param <K> 键类型
 * @param <V> 值类型
 * @since 3.0.2
 */
public class CaseInsensitiveMap<K, V> extends CustomKeyMap<K, V> {
	private static final long serialVersionUID = 4043263744224569870L;

	//------------------------------------------------------------------------- Constructor start
	/**
	 * 构造
	 */
	public CaseInsensitiveMap() {
		this(DEFAULT_INITIAL_CAPACITY);
	}

	/**
	 * 构造
	 * 
	 * @param initialCapacity 初始大小
	 */
	public CaseInsensitiveMap(int initialCapacity) {
		this(initialCapacity, DEFAULT_LOAD_FACTOR);
	}

	/**
	 * 构造
	 * 
	 * @param m Map
	 */
	public CaseInsensitiveMap(Map<? extends K, ? extends V> m) {
		this(DEFAULT_LOAD_FACTOR, m);
	}

	/**
	 * 构造
	 * 
	 * @param loadFactor 加载因子
	 * @param m Map
	 * @since 3.1.2
	 */
	public CaseInsensitiveMap(float loadFactor, Map<? extends K, ? extends V> m) {
		this(m.size(), loadFactor);
		this.putAll(m);
	}
	
	/**
	 * 构造
	 * 
	 * @param initialCapacity 初始大小
	 * @param loadFactor 加载因子
	 */
	public CaseInsensitiveMap(int initialCapacity, float loadFactor) {
		super(new HashMap<>(initialCapacity, loadFactor));
	}
	//------------------------------------------------------------------------- Constructor end

	/**
	 * 将Key转为小写
	 * 
	 * @param key KEY
	 * @return 小写KEY
	 */
	@Override
	protected Object customKey(Object key) {
		if (key instanceof CharSequence) {
			key = key.toString().toLowerCase();
		}
		return key;
	}
}
