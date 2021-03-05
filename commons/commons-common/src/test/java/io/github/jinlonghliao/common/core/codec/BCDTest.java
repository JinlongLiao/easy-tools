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
package io.github.jinlonghliao.common.core.codec;

import org.junit.Assert;
import org.junit.Test;

import io.github.jinlonghliao.common.core.codec.BCD;

public class BCDTest {
	
	@Test
	public void bcdTest(){
		String strForTest = "123456ABCDEF";
		
		//转BCD
		byte[] bcd = BCD.strToBcd(strForTest);
		String str = BCD.bcdToStr(bcd);
		//解码BCD
		Assert.assertEquals(strForTest, str);
	}
}
