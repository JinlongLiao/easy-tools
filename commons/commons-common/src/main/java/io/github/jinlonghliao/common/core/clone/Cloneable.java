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
package io.github.jinlonghliao.common.core.clone;

/**
 * 克隆支持接口
 * @author Looly
 *
 * @param <T> 实现克隆接口的类型
 */
public interface Cloneable<T> extends java.lang.Cloneable{
	
	/**
	 * 克隆当前对象，浅复制
	 * @return 克隆后的对象
	 */
	T clone();
}
