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

public class CamelCaseMapTest {
	
	@Test
	public void caseInsensitiveMapTest() {
		CamelCaseMap<String, String> map = new CamelCaseMap<>();
		map.put("customKey", "OK");
		Assert.assertEquals("OK", map.get("customKey"));
		Assert.assertEquals("OK", map.get("custom_key"));
	}
	
	@Test
	public void caseInsensitiveLinkedMapTest() {
		CamelCaseLinkedMap<String, String> map = new CamelCaseLinkedMap<>();
		map.put("customKey", "OK");
		Assert.assertEquals("OK", map.get("customKey"));
		Assert.assertEquals("OK", map.get("custom_key"));
	}
}
