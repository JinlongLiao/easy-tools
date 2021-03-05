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

import org.junit.Ignore;
import org.junit.Test;

import io.github.jinlonghliao.common.core.io.file.FileCopier;

/**
 * 文件拷贝单元测试
 * @author Looly
 *
 */
public class FileCopierTest {
	
	@Test
	@Ignore
	public void dirCopyTest() {
		FileCopier copier = FileCopier.create("D:\\Java", "e:/eclipse/eclipse2.zip");
		copier.copy();
	}
	
	@Test
	@Ignore
	public void dirCopyTest2() {
		//测试带.的文件夹复制
		FileCopier copier = FileCopier.create("D:\\workspace\\java\\.metadata", "D:\\workspace\\java\\.metadata\\temp");
		copier.copy();
		
		FileUtil.copy("D:\\workspace\\java\\looly\\hutool\\.git", "D:\\workspace\\java\\temp", true);
	}
	
	@Test(expected = IORuntimeException.class)
	public void dirCopySubTest() {
		//测试父目录复制到子目录报错
		FileCopier copier = FileCopier.create("D:\\workspace\\java\\.metadata", "D:\\workspace\\java\\.metadata\\temp");
		copier.copy();
	}
	
	@Test
	@Ignore
	public void copyFileToDirTest() {
		FileCopier copier = FileCopier.create("d:/GReen_Soft/XshellXftpPortable.zip", "c:/hp/");
		copier.copy();
	}
}
