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

/**
 * 规范化对象生成工具
 *
 * @author looly
 * @since 5.4.3
 */
public class InternUtil {

	/**
	 * 创建WeakHshMap实现的字符串规范化器
	 *
	 * @param <T> 规范对象的类型
	 * @return {@link Interner}
	 */
	public static <T> Interner<T> createWeakInterner(){
		return new WeakInterner<>();
	}

	/**
	 * 创建JDK默认实现的字符串规范化器
	 *
	 * @return {@link Interner}
	 * @see String#intern()
	 */
	public static Interner<String> createJdkInterner(){
		return new JdkStringInterner();
	}

	/**
	 * 创建字符串规范化器
	 *
	 * @param isWeak 是否创建使用WeakHashMap实现的Interner
	 * @return {@link Interner}
	 */
	public static Interner<String> createStringInterner(boolean isWeak){
		return isWeak ? createWeakInterner() : createJdkInterner();
	}
}
