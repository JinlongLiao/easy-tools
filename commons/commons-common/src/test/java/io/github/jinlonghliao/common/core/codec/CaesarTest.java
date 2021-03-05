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
 package io.github.jinlonghliao.common.core.codec;

import org.junit.Assert;
import org.junit.Test;

public class CaesarTest {
	
	@Test
	public void caesarTest() {
		String str = "1f2e9df6131b480b9fdddc633cf24996";

		String encode = Caesar.encode(str, 3);
		Assert.assertEquals("1H2G9FH6131D480D9HFFFE633EH24996", encode);

		String decode = Caesar.decode(encode, 3);
		Assert.assertEquals(str, decode);
	}
}
