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

import java.util.Calendar;

public class CalendarUtilTest {

	@Test
	public void formatChineseDate(){
		Calendar calendar = DateUtil.parse("2018-02-24 12:13:14").toCalendar();
		final String chineseDate = CalendarUtil.formatChineseDate(calendar, false);
		Assert.assertEquals("二〇一八年二月二十四日", chineseDate);
		final String chineseDateTime = CalendarUtil.formatChineseDate(calendar, true);
		Assert.assertEquals("二〇一八年二月二十四日一十二时一十三分一十四秒", chineseDateTime);
	}
}
