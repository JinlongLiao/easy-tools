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
 package io.github.jinlonghliao.common.core.lang.caller;

import org.junit.Assert;
import org.junit.Test;

/**
 * {@link CallerUtil} 单元测试
 * @author Looly
 *
 */
public class CallerTest {
	
	@Test
	public void getCallerTest() {
		Class<?> caller = CallerUtil.getCaller();
		Assert.assertEquals(this.getClass(), caller);
		
		Class<?> caller0 = CallerUtil.getCaller(0);
		Assert.assertEquals(CallerUtil.class, caller0);
		
		Class<?> caller1 = CallerUtil.getCaller(1);
		Assert.assertEquals(this.getClass(), caller1);
	}
	
	@Test
	public void getCallerCallerTest() {
		Class<?> callerCaller = CallerTestClass.getCaller();
		Assert.assertEquals(this.getClass(), callerCaller);
	}
	
	private static class CallerTestClass{
		public static Class<?> getCaller(){
			return CallerUtil.getCallerCaller();
		}
	}
}
