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

public class CharUtilTest {
	
	@Test
	public void trimTest() {
		//此字符串中的第一个字符为不可见字符: '\u202a'
		String str = "‪C:/Users/maple/Desktop/tone.txt";
		Assert.assertEquals('\u202a', str.charAt(0));
		Assert.assertTrue(CharUtil.isBlankChar(str.charAt(0)));
	}
	
	@Test
	public void isEmojiTest() {
		String a = "莉🌹";
		Assert.assertFalse(CharUtil.isEmoji(a.charAt(0)));
		Assert.assertTrue(CharUtil.isEmoji(a.charAt(1)));
		
	}

	@Test
	public void isCharTest(){
		char a = 'a';
		Assert.assertTrue(CharUtil.isChar(a));
	}

	@Test
	public void isBlankCharTest(){
		char a = '\u00A0';
		Assert.assertTrue(CharUtil.isBlankChar(a));

		char a2 = '\u0020';
		Assert.assertTrue(CharUtil.isBlankChar(a2));

		char a3 = '\u3000';
		Assert.assertTrue(CharUtil.isBlankChar(a3));
	}
}
