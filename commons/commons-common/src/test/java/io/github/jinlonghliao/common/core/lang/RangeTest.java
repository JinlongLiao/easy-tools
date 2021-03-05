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
package io.github.jinlonghliao.common.core.lang;

import io.github.jinlonghliao.common.core.date.DateField;
import io.github.jinlonghliao.common.core.date.DateTime;
import io.github.jinlonghliao.common.core.date.DateUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * {@link Range} 单元测试
 * @author Looly
 *
 */
public class RangeTest {
	
	@Test
	public void dateRangeTest() {
		DateTime start = DateUtil.parse("2017-01-01");
		DateTime end = DateUtil.parse("2017-01-02");
		
		final Range<DateTime> range = new Range<>(start, end, (current, end1, index) -> {
			if (current.isAfterOrEquals(end1)) {
				return null;
			}
			return current.offsetNew(DateField.DAY_OF_YEAR, 1);
		});
		
		Assert.assertTrue(range.hasNext());
		Assert.assertEquals(range.next(), DateUtil.parse("2017-01-01"));
		Assert.assertTrue(range.hasNext());
		Assert.assertEquals(range.next(), DateUtil.parse("2017-01-02"));
		Assert.assertFalse(range.hasNext());
	}
	
	@Test
	public void intRangeTest() {
		final Range<Integer> range = new Range<>(1, 1, (current, end, index) -> current >= end ? null : current + 10);
		
		Assert.assertTrue(range.hasNext());
		Assert.assertEquals(Integer.valueOf(1), range.next());
		Assert.assertFalse(range.hasNext());
	}
}
