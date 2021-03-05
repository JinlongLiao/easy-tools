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

import org.junit.Assert;
import org.junit.Test;

public class HashUtilTest {

	@Test
	public void cityHash128Test(){
		String s="Google发布的Hash计算算法：CityHash64 与 CityHash128";
		final long[] hash = HashUtil.cityHash128(StrUtil.utf8Bytes(s));
		Assert.assertEquals(0x5944f1e788a18db0L, hash[0]);
		Assert.assertEquals(0xc2f68d8b2bf4a5cfL, hash[1]);
	}

	@Test
	public void cityHash64Test(){
		String s="Google发布的Hash计算算法：CityHash64 与 CityHash128";
		final long hash = HashUtil.cityHash64(StrUtil.utf8Bytes(s));
		Assert.assertEquals(0x1d408f2bbf967e2aL, hash);
	}

	@Test
	public void cityHash32Test(){
		String s="Google发布的Hash计算算法：CityHash64 与 CityHash128";
		final int hash = HashUtil.cityHash32(StrUtil.utf8Bytes(s));
		Assert.assertEquals(0xa8944fbe, hash);
	}
}
