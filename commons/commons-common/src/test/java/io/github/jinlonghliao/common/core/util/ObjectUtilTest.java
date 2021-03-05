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

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import io.github.jinlonghliao.common.core.clone.CloneSupport;
import io.github.jinlonghliao.common.core.collection.CollUtil;

public class ObjectUtilTest {

	@Test
	public void cloneTest() {
		Obj obj = new Obj();
		Obj obj2 = ObjectUtil.clone(obj);
		Assert.assertEquals("OK", obj2.doSomeThing());
	}

	static class Obj extends CloneSupport<Obj> {
		public String doSomeThing() {
			return "OK";
		}
	}

	@Test
	public void toStringTest() {
		ArrayList<String> strings = CollUtil.newArrayList("1", "2");
		String result = ObjectUtil.toString(strings);
		Assert.assertEquals("[1, 2]", result);
	}
}
