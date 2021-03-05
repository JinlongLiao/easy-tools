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

import io.github.jinlonghliao.common.core.collection.CollUtil;
import io.github.jinlonghliao.common.core.lang.Console;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.math.RoundingMode;
import java.util.List;
import java.util.Set;

public class RandomUtilTest {
	
	@Test
	public void randomEleSetTest(){
		Set<Integer> set = RandomUtil.randomEleSet(CollUtil.newArrayList(1, 2, 3, 4, 5, 6), 2);
		Assert.assertEquals(set.size(), 2);
	}
	
	@Test
	public void randomElesTest(){
		List<Integer> result = RandomUtil.randomEles(CollUtil.newArrayList(1, 2, 3, 4, 5, 6), 2);
		Assert.assertEquals(result.size(), 2);
	}
	
	@Test
	public void randomDoubleTest() {
		double randomDouble = RandomUtil.randomDouble(0, 1, 0, RoundingMode.HALF_UP);
		Assert.assertTrue(randomDouble <= 1);
	}
	
	@Test
	@Ignore
	public void randomBooleanTest() {
		Console.log(RandomUtil.randomBoolean());
	}

	@Test
	public void randomNumberTest() {
		final char c = RandomUtil.randomNumber();
		Assert.assertTrue(c <= '9');
	}
}
