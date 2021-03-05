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

import io.github.jinlonghliao.common.core.date.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class DictTest {
	@Test
	public void dictTest(){
		Dict dict = Dict.create()
				.set("key1", 1)//int
				.set("key2", 1000L)//long
				.set("key3", DateTime.now());//Date
		
		Long v2 = dict.getLong("key2");
		Assert.assertEquals(Long.valueOf(1000L), v2);
	}

	@Test
	public void dictTest2(){
		final Dict dict = new Dict(true);
		Map<String, Object> map = new HashMap<>();
		map.put("A", 1);

		dict.putAll(map);

		Assert.assertEquals(1, dict.get("A"));
		Assert.assertEquals(1, dict.get("a"));
	}

	@Test
	public void ofTest(){
		Dict dict = Dict.of(
				"RED", "#FF0000",
				"GREEN", "#00FF00",
				"BLUE", "#0000FF"
		);

		Assert.assertEquals("#FF0000", dict.get("RED"));
		Assert.assertEquals("#00FF00", dict.get("GREEN"));
		Assert.assertEquals("#0000FF", dict.get("BLUE"));
	}
}
