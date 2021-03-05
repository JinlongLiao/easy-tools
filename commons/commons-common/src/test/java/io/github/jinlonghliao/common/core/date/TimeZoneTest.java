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

import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;

import io.github.jinlonghliao.common.core.date.format.FastDateFormat;

public class TimeZoneTest {
	
	@Test
	public void timeZoneConvertTest() {
		DateTime dt = DateUtil.parse("2018-07-10 21:44:32", //
				FastDateFormat.getInstance(DatePattern.NORM_DATETIME_PATTERN, TimeZone.getTimeZone("GMT+8:00")));
		Assert.assertEquals("2018-07-10 21:44:32", dt.toString());
		
		dt.setTimeZone(TimeZone.getTimeZone("Europe/London"));
		int hour = dt.getField(DateField.HOUR_OF_DAY);
		Assert.assertEquals(14, hour);
		Assert.assertEquals("2018-07-10 14:44:32", dt.toString());
	}
}
