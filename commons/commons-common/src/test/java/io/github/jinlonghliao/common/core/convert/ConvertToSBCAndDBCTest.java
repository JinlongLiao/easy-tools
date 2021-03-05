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

import org.junit.Assert;
import org.junit.Test;

/**
 * 类型转换工具单元测试
 * 全角半角转换
 * 
 * @author Looly
 *
 */
public class ConvertToSBCAndDBCTest {

	@Test
	public void toSBCTest() {
		String a = "123456789";
		String sbc = Convert.toSBC(a);
		Assert.assertEquals("１２３４５６７８９", sbc);
	}
	
	@Test
	public void toDBCTest() {
		String a = "１２３４５６７８９";
		String dbc = Convert.toDBC(a);
		Assert.assertEquals("123456789", dbc);
	}
}
