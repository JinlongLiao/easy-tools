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
package io.github.jinlonghliao.common.core.io.unit;

import org.junit.Assert;
import org.junit.Test;

public class DataSizeUtilTest {

	@Test
	public void parseTest(){
		long parse = DataSizeUtil.parse("3M");
		Assert.assertEquals(3145728, parse);

		parse = DataSizeUtil.parse("3m");
		Assert.assertEquals(3145728, parse);

		parse = DataSizeUtil.parse("3MB");
		Assert.assertEquals(3145728, parse);

		parse = DataSizeUtil.parse("3mb");
		Assert.assertEquals(3145728, parse);
	}

	@Test
	public void formatTest(){
		final String format = DataSizeUtil.format(Long.MAX_VALUE);
		Assert.assertEquals("8,192 EB", format);
	}
}
