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

import io.github.jinlonghliao.common.core.lang.Console;
import io.github.jinlonghliao.common.core.swing.clipboard.ClipboardUtil;
import org.junit.Ignore;
import org.junit.Test;

public class ClipboardMonitorTest {

	@Test
	@Ignore
	public void monitorTest() {
		// 第一个监听
		ClipboardUtil.listen((clipboard, contents) -> {
			Object object = ClipboardUtil.getStr(contents);
			Console.log("1# {}", object);
			return contents;
		}, false);
		
		// 第二个监听
		ClipboardUtil.listen((clipboard, contents) -> {
			Object object = ClipboardUtil.getStr(contents);
			Console.log("2# {}", object);
			return contents;
		});

	}
}
