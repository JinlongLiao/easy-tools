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
 package io.github.jinlonghliao.common.core.lang.intern;

/**
 * JDK中默认的字符串规范化实现
 *
 * @author looly
 * @since 5.4.3
 */
public class JdkStringInterner implements Interner<String>{
	@Override
	public String intern(String sample) {
		if(null == sample){
			return null;
		}
		return sample.intern();
	}
}
