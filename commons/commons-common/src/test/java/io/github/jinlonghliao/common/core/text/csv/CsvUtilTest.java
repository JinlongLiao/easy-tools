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
package io.github.jinlonghliao.common.core.text.csv;

import io.github.jinlonghliao.common.core.io.FileUtil;
import io.github.jinlonghliao.common.core.lang.Assert;
import io.github.jinlonghliao.common.core.util.CharsetUtil;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

public class CsvUtilTest {
	
	@Test
	public void readTest() {
		CsvReader reader = CsvUtil.getReader();
		//从文件中读取CSV数据
		CsvData data = reader.read(FileUtil.file("test.csv"));
		List<CsvRow> rows = data.getRows();
		for (CsvRow csvRow : rows) {
			Assert.notEmpty(csvRow.getRawList());
		}
	}

	@Test
	public void readTest2() {
		CsvReader reader = CsvUtil.getReader();
		reader.read(FileUtil.getUtf8Reader("test.csv"), (csvRow)-> Assert.notEmpty(csvRow.getRawList()));
	}
	
	@Test
	@Ignore
	public void writeTest() {
		CsvWriter writer = CsvUtil.getWriter("e:/testWrite.csv", CharsetUtil.CHARSET_UTF_8);
		writer.write(
				new String[] {"a1", "b1", "c1", "123345346456745756756785656"}, 
				new String[] {"a2", "b2", "c2"}, 
				new String[] {"a3", "b3", "c3"}
		);
	}
	
}
