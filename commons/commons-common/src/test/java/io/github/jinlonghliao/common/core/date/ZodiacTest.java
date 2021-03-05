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
package io.github.jinlonghliao.common.core.date;

import org.junit.Assert;
import org.junit.Test;

public class ZodiacTest {
	
	@Test
	public void getZodiacTest() {
		Assert.assertEquals("摩羯座", Zodiac.getZodiac(Month.JANUARY, 19));
		Assert.assertEquals("水瓶座", Zodiac.getZodiac(Month.JANUARY, 20));
		Assert.assertEquals("巨蟹座", Zodiac.getZodiac(6, 17));
	}
	
	@Test
	public void getChineseZodiacTest() {
		Assert.assertEquals("狗", Zodiac.getChineseZodiac(1994));
		Assert.assertEquals("狗", Zodiac.getChineseZodiac(2018));
		Assert.assertEquals("猪", Zodiac.getChineseZodiac(2019));
	}
}
