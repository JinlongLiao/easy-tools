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

public class ChineseDateTest {

	@Test
	public void chineseDateTest() {
		ChineseDate date = new ChineseDate(DateUtil.parseDate("2020-01-25"));
		Assert.assertEquals(2020, date.getChineseYear());

		Assert.assertEquals(1, date.getMonth());
		Assert.assertEquals("一月", date.getChineseMonth());
		Assert.assertEquals("正月", date.getChineseMonthName());


		Assert.assertEquals(1, date.getDay());
		Assert.assertEquals("初一", date.getChineseDay());

		Assert.assertEquals("庚子", date.getCyclical());
		Assert.assertEquals("鼠", date.getChineseZodiac());
		Assert.assertEquals("春节", date.getFestivals());
		Assert.assertEquals("庚子鼠年 正月初一", date.toString());

		date = new ChineseDate(DateUtil.parseDate("2020-01-14"));
		Assert.assertEquals("己亥猪年 腊月二十", date.toString());
		date = new ChineseDate(DateUtil.parseDate("2020-01-24"));
		Assert.assertEquals("己亥猪年 腊月三十", date.toString());

		Assert.assertEquals("2019-12-30", date.toStringNormal());
	}

	@Test
	public void toStringNormalTest(){
		ChineseDate date = new ChineseDate(DateUtil.parseDate("2020-03-1"));
		Assert.assertEquals("2020-02-08", date.toStringNormal());
	}

	@Test
	public void parseTest(){
		ChineseDate date = new ChineseDate(DateUtil.parseDate("1996-07-14"));
		Assert.assertEquals("丙子鼠年 五月廿九", date.toString());

		date = new ChineseDate(DateUtil.parseDate("1996-07-15"));
		Assert.assertEquals("丙子鼠年 五月三十", date.toString());
	}
	@Test
	public void getCyclicalYMDTest(){
		//通过公历构建
		ChineseDate chineseDate = new ChineseDate(DateUtil.parseDate("1993-01-06"));
		String cyclicalYMD = chineseDate.getCyclicalYMD();
		Assert.assertEquals("壬申年癸丑月丁亥日",cyclicalYMD);
	}

	@Test
	public void getCyclicalYMDTest2(){
		//通过农历构建
		ChineseDate chineseDate = new ChineseDate(1992,12,14);
		String cyclicalYMD = chineseDate.getCyclicalYMD();
		Assert.assertEquals("壬申年癸丑月丁亥日",cyclicalYMD);
	}

	@Test
	public void getCyclicalYMDTest3(){
		//通过公历构建
		ChineseDate chineseDate = new ChineseDate(DateUtil.parseDate("2020-08-28"));
		String cyclicalYMD = chineseDate.getCyclicalYMD();
		Assert.assertEquals("庚子年甲申月癸卯日",cyclicalYMD);
	}

	@Test
	public void getChineseMonthTest(){
		ChineseDate chineseDate = new ChineseDate(2020,6,15);
		Assert.assertEquals("六月", chineseDate.getChineseMonth());

		chineseDate = new ChineseDate(2020,4,15);
		Assert.assertEquals("闰四月", chineseDate.getChineseMonth());
	}
}
