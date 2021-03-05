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
package io.github.jinlonghliao.common.core.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * HexUtil单元测试
 * @author Looly
 *
 */
public class HexUtilTest {
	
	@Test
	public void hexStrTest(){
		String str = "我是一个字符串";
		
		String hex = HexUtil.encodeHexStr(str, CharsetUtil.CHARSET_UTF_8);
		String decodedStr = HexUtil.decodeHexStr(hex);
		
		Assert.assertEquals(str, decodedStr);
	}
	
	@Test
	public void toUnicodeHexTest() {
		String unicodeHex = HexUtil.toUnicodeHex('\u2001');
		Assert.assertEquals("\\u2001", unicodeHex);
		
		unicodeHex = HexUtil.toUnicodeHex('你');
		Assert.assertEquals("\\u4f60", unicodeHex);
	}
	
	@Test
	public void isHexNumberTest() {
		String a = "0x3544534F444";
		boolean isHex = HexUtil.isHexNumber(a);
		Assert.assertTrue(isHex);
	}

	@Test
	public void decodeTest(){
		String str = "e8c670380cb220095268f40221fc748fa6ac39d6e930e63c30da68bad97f885d";
		Assert.assertArrayEquals(HexUtil.decodeHex(str),
				HexUtil.decodeHex(str.toUpperCase()));
	}
}
