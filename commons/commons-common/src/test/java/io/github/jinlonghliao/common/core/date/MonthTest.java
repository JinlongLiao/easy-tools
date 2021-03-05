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

public class MonthTest {

	@SuppressWarnings("ConstantConditions")
	@Test
	public void getLastDayTest(){
		int lastDay = Month.of(Calendar.JANUARY).getLastDay(false);
		Assert.assertEquals(31, lastDay);
		lastDay = Month.of(Calendar.FEBRUARY).getLastDay(false);
		Assert.assertEquals(28, lastDay);
		lastDay = Month.of(Calendar.FEBRUARY).getLastDay(true);
		Assert.assertEquals(29, lastDay);
		lastDay = Month.of(Calendar.MARCH).getLastDay(true);
		Assert.assertEquals(31, lastDay);
		lastDay = Month.of(Calendar.APRIL).getLastDay(true);
		Assert.assertEquals(30, lastDay);
		lastDay = Month.of(Calendar.MAY).getLastDay(true);
		Assert.assertEquals(31, lastDay);
		lastDay = Month.of(Calendar.JUNE).getLastDay(true);
		Assert.assertEquals(30, lastDay);
		lastDay = Month.of(Calendar.JULY).getLastDay(true);
		Assert.assertEquals(31, lastDay);
		lastDay = Month.of(Calendar.AUGUST).getLastDay(true);
		Assert.assertEquals(31, lastDay);
		lastDay = Month.of(Calendar.SEPTEMBER).getLastDay(true);
		Assert.assertEquals(30, lastDay);
		lastDay = Month.of(Calendar.OCTOBER).getLastDay(true);
		Assert.assertEquals(31, lastDay);
		lastDay = Month.of(Calendar.NOVEMBER).getLastDay(true);
		Assert.assertEquals(30, lastDay);
		lastDay = Month.of(Calendar.DECEMBER).getLastDay(true);
		Assert.assertEquals(31, lastDay);
	}
}
