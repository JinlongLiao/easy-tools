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

import org.junit.Assert;
import org.junit.Test;

import io.github.jinlonghliao.common.core.collection.CollUtil;

public class WeightRandomTest {

	@Test
	public void weightRandomTest() {
		WeightRandom<String> random = WeightRandom.create();
		random.add("A", 10);
		random.add("B", 50);
		random.add("C", 100);

		String result = random.next();
		Assert.assertTrue(CollUtil.newArrayList("A", "B", "C").contains(result));
	}
}
