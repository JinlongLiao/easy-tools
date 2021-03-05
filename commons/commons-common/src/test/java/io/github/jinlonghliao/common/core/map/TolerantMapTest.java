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

import io.github.jinlonghliao.common.core.util.ObjectUtil;
import io.github.jinlonghliao.common.core.util.RandomUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class TolerantMapTest {

	private final TolerantMap<String, String> map = TolerantMap.of(new HashMap<>(), "default");

	@Before
	public void before() {
		map.put("monday", "星期一");
		map.put("tuesday", "星期二");
	}

	@Test
	public void testSerialize() {
		byte[] bytes = ObjectUtil.serialize(map);
		TolerantMap<String, String> serializedMap = ObjectUtil.deserialize(bytes);
		assert serializedMap != map;
		assert map.equals(serializedMap);
	}

	@Test
	public void testClone() {
		TolerantMap<String, String> clonedMap = ObjectUtil.clone(map);
		assert clonedMap != map;
		assert map.equals(clonedMap);
	}

	@Test
	public void testGet() {
		assert "星期二".equals(map.get("tuesday"));
		assert "default".equals(map.get(RandomUtil.randomString(6)));
	}
}
