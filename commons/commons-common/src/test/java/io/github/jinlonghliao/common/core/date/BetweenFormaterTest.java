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
 package io.github.jinlonghliao.common.core.date;

import io.github.jinlonghliao.common.core.date.BetweenFormater.Level;
import org.junit.Assert;
import org.junit.Test;

public class BetweenFormaterTest {
	
	@Test
	public void formatTest(){
		long betweenMs = DateUtil.betweenMs(DateUtil.parse("2017-01-01 22:59:59"), DateUtil.parse("2017-01-02 23:59:58"));
		BetweenFormater formater = new BetweenFormater(betweenMs, Level.MILLISECOND, 1);
		Assert.assertEquals(formater.toString(), "1天");
	}
	
	@Test
	public void formatBetweenTest(){
		long betweenMs = DateUtil.betweenMs(DateUtil.parse("2018-07-16 11:23:19"), DateUtil.parse("2018-07-16 11:23:20"));
		BetweenFormater formater = new BetweenFormater(betweenMs, Level.SECOND, 1);
		Assert.assertEquals(formater.toString(), "1秒");
	}
	
	@Test
	public void formatTest2(){
		BetweenFormater formater = new BetweenFormater(584, Level.SECOND, 1);
		Assert.assertEquals(formater.toString(), "0秒");
	}
}
