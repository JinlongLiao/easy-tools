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
package io.github.jinlonghliao.common.core.io;

import io.github.jinlonghliao.common.core.lang.Console;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

/**
 * 文件类型判断单元测试
 * @author Looly
 *
 */
public class FileTypeUtilTest {
	
	@Test
	@Ignore
	public void fileTypeUtilTest() {
		File file = FileUtil.file("hutool.jpg");
		String type = FileTypeUtil.getType(file);
		Assert.assertEquals("jpg", type);
		
		FileTypeUtil.putFileType("ffd8ffe000104a464946", "new_jpg");
		String newType = FileTypeUtil.getType(file);
		Assert.assertEquals("new_jpg", newType);
	}
	
	@Test
	@Ignore
	public void emptyTest() {
		File file = FileUtil.file("d:/empty.txt");
		String type = FileTypeUtil.getType(file);
		Console.log(type);
	}

	@Test
	@Ignore
	public void docTest() {
		File file = FileUtil.file("f:/test/test.doc");
		String type = FileTypeUtil.getType(file);
		Console.log(type);
	}
}
