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
package io.github.jinlonghliao.common.core.convert;

/**
 * 转换器接口，实现类型转换
 * 
 * @param <T> 转换到的目标类型
 * @author Looly
 */
public interface Converter<T> {

	/**
	 * 转换为指定类型<br>
	 * 如果类型无法确定，将读取默认值的类型做为目标类型
	 * 
	 * @param value 原始值
	 * @param defaultValue 默认值
	 * @return 转换后的值
	 * @throws IllegalArgumentException 无法确定目标类型，且默认值为{@code null}，无法确定类型
	 */
	T convert(Object value, T defaultValue) throws IllegalArgumentException;

}