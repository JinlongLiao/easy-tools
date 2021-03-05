/*
 * Copyright 2020-2021.
 * 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
 package io.github.jinlonghliao.common.core.map;

import java.util.LinkedHashMap;

/**
 * 固定大小的{@link LinkedHashMap} 实现
 * 
 * @author looly
 *
 * @param <K> 键类型
 * @param <V> 值类型
 */
public class FixedLinkedHashMap<K, V> extends LinkedHashMap<K, V> {
	private static final long serialVersionUID = -629171177321416095L;

	/** 容量，超过此容量自动删除末尾元素 */
	private int capacity;
	
	/**
	 * 构造
	 * 
	 * @param capacity 容量，实际初始容量比容量大1
	 */
	public FixedLinkedHashMap(int capacity) {
		super(capacity + 1, 1.0f, true);
		this.capacity = capacity;
	}

	/**
	 * 获取容量
	 * 
	 * @return 容量
	 */
	public int getCapacity() {
		return this.capacity;
	}

	/**
	 * 设置容量
	 * 
	 * @param capacity 容量
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
		//当链表元素大于容量时，移除最老（最久未被使用）的元素
		return size() > this.capacity;
	}

}
