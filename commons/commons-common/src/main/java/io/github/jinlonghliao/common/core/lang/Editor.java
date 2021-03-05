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
package io.github.jinlonghliao.common.core.lang;

/**
 * 编辑器接口，常用于对于集合中的元素做统一编辑<br>
 * 此编辑器两个作用：
 * 
 * <pre>
 * 1、如果返回值为<code>null</code>，表示此值被抛弃 
 * 2、对对象做修改
 * </pre>
 * 
 * @param <T> 被编辑对象类型
 * @author Looly
 */
@FunctionalInterface
public interface Editor<T> {
	/**
	 * 修改过滤后的结果
	 * 
	 * @param t 被过滤的对象
	 * @return 修改后的对象，如果被过滤返回<code>null</code>
	 */
	T edit(T t);
}
