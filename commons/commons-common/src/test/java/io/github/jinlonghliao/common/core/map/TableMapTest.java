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
package io.github.jinlonghliao.common.core.map;

import org.junit.Assert;
import org.junit.Test;

public class TableMapTest {

	@Test
	public void getTest(){
		TableMap<String, Integer> tableMap = new TableMap<>(16);
		tableMap.put("aaa", 111);
		tableMap.put("bbb", 222);

		Assert.assertEquals(new Integer(111), tableMap.get("aaa"));
		Assert.assertEquals(new Integer(222), tableMap.get("bbb"));

		Assert.assertEquals("aaa", tableMap.getKey(111));
		Assert.assertEquals("bbb", tableMap.getKey(222));
	}
}
