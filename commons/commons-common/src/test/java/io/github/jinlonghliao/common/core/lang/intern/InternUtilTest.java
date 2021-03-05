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
package io.github.jinlonghliao.common.core.lang.intern;

import io.github.jinlonghliao.common.core.util.RandomUtil;
import org.junit.Assert;
import org.junit.Test;

public class InternUtilTest {

	/**
	 * 检查规范字符串是否相同
	 */
	@SuppressWarnings("StringOperationCanBeSimplified")
	@Test
	public void weakTest(){
		final Interner<String> interner = InternUtil.createWeakInterner();
		String a1 = RandomUtil.randomString(RandomUtil.randomInt(100));
		String a2 = new String(a1);

		Assert.assertNotSame(a1, a2);

		Assert.assertSame(interner.intern(a1), interner.intern(a2));
	}

}
