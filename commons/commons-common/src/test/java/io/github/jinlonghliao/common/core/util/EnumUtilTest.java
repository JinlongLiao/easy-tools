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
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * EnumUtil单元测试
 * 
 * @author looly
 *
 */
public class EnumUtilTest {

	@Test
	public void getNamesTest() {
		List<String> names = EnumUtil.getNames(TestEnum.class);
		Assert.assertEquals(CollUtil.newArrayList("TEST1", "TEST2", "TEST3"), names);
	}
	
	@Test
	public void getFieldValuesTest() {
		List<Object> types = EnumUtil.getFieldValues(TestEnum.class, "type");
		Assert.assertEquals(CollUtil.newArrayList("type1", "type2", "type3"), types);
	}
	
	@Test
	public void getFieldNamesTest() {
		List<String> names = EnumUtil.getFieldNames(TestEnum.class);
		Assert.assertEquals(CollUtil.newArrayList("type", "name"), names);
	}
	
	@Test
	public void likeValueOfTest() {
		TestEnum value = EnumUtil.likeValueOf(TestEnum.class, "type2");
		Assert.assertEquals(TestEnum.TEST2, value);
	}
	
	@Test
	public void getEnumMapTest() {
		Map<String,TestEnum> enumMap = EnumUtil.getEnumMap(TestEnum.class);
		Assert.assertEquals(TestEnum.TEST1, enumMap.get("TEST1"));
	}
	
	@Test
	public void getNameFieldMapTest() {
		Map<String, Object> enumMap = EnumUtil.getNameFieldMap(TestEnum.class, "type");
		assert enumMap != null;
		Assert.assertEquals("type1", enumMap.get("TEST1"));
	}
	
	public enum TestEnum{
		TEST1("type1"), TEST2("type2"), TEST3("type3");

		TestEnum(String type) {
			this.type = type;
		}

		private final String type;
		private String name;
		
		public String getType() {
			return this.type;
		}
		
		public String getName() {
			return this.name;
		}
	}
}
