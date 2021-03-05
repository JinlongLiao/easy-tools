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
 package io.github.jinlonghliao.common.core.lang;

import org.junit.Assert;
import org.junit.Test;

import io.github.jinlonghliao.common.core.text.StrFormatter;

public class StrFormatterTest {
	
	@Test
	public void formatTest(){
		//通常使用
		String result1 = StrFormatter.format("this is {} for {}", "a", "b");
		Assert.assertEquals("this is a for b", result1);
		
		//转义{}
		String result2 = StrFormatter.format("this is \\{} for {}", "a", "b");
		Assert.assertEquals("this is {} for a", result2);
		
		//转义\
		String result3 = StrFormatter.format("this is \\\\{} for {}", "a", "b");
		Assert.assertEquals("this is \\a for b", result3);
	}
}
