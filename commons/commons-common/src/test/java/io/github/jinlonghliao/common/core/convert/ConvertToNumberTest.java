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
package io.github.jinlonghliao.common.core.convert;

import io.github.jinlonghliao.common.core.date.DateTime;
import io.github.jinlonghliao.common.core.date.DateUtil;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

public class ConvertToNumberTest {
	@Test
	public void dateToLongTest(){
		final DateTime date = DateUtil.parse("2020-05-17 12:32:00");
		final Long dateLong = Convert.toLong(date);
		assert date != null;
		Assert.assertEquals(date.getTime(), dateLong.longValue());
	}

	@Test
	public void dateToIntTest(){
		final DateTime date = DateUtil.parse("2020-05-17 12:32:00");
		final Integer dateInt = Convert.toInt(date);
		assert date != null;
		Assert.assertEquals((int)date.getTime(), dateInt.intValue());
	}

	@Test
	public void dateToAtomicLongTest(){
		final DateTime date = DateUtil.parse("2020-05-17 12:32:00");
		final AtomicLong dateLong = Convert.convert(AtomicLong.class, date);
		assert date != null;
		Assert.assertEquals(date.getTime(), dateLong.longValue());
	}

	@Test
	public void toBigDecimalTest(){
		BigDecimal bigDecimal = Convert.toBigDecimal("1.1f");
		Assert.assertEquals(1.1f, bigDecimal.floatValue(), 1);

		bigDecimal = Convert.toBigDecimal("1L");
		Assert.assertEquals(1L, bigDecimal.longValue());
	}
}
