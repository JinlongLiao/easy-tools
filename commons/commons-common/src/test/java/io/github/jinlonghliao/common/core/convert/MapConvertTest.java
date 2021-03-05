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

import io.github.jinlonghliao.common.core.map.MapBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Map转换单元测试
 * 
 * @author looly
 *
 */
public class MapConvertTest {

	@Test
	public void beanToMapTest() {
		User user = new User();
		user.setName("AAA");
		user.setAge(45);

		HashMap<?, ?> map = Convert.convert(HashMap.class, user);
		Assert.assertEquals("AAA", map.get("name"));
		Assert.assertEquals(45, map.get("age"));
	}

	@Test
	public void mapToMapTest() {
		Map<String, Object> srcMap = MapBuilder
				.create(new HashMap<String, Object>())
				.put("name", "AAA")
				.put("age", 45).map();

		LinkedHashMap<?, ?> map = Convert.convert(LinkedHashMap.class, srcMap);
		Assert.assertEquals("AAA", map.get("name"));
		Assert.assertEquals(45, map.get("age"));
	}

	public static class User {
		private String name;
		private int age;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}
	}
}
