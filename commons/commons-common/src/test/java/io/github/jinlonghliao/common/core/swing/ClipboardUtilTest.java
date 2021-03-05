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
package io.github.jinlonghliao.common.core.swing;

import org.junit.Assert;
import org.junit.Test;

import io.github.jinlonghliao.common.core.swing.clipboard.ClipboardUtil;

/**
 * 剪贴板工具类单元测试
 * 
 * @author looly
 *
 */
public class ClipboardUtilTest {

	@Test
	public void setAndGetStrTest() {
		try {
			ClipboardUtil.setStr("test");

			String test = ClipboardUtil.getStr();
			Assert.assertEquals("test", test);
		} catch (java.awt.HeadlessException e) {
			// 忽略 No X11 DISPLAY variable was set, but this program performed an operation which requires it.
			// ignore
		}
	}
}
