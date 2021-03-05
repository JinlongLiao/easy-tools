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

import io.github.jinlonghliao.common.core.io.IoUtil;
import io.github.jinlonghliao.common.core.util.StrUtil;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringReader;

public class CsvParserTest {
	
	@Test
	public void parseTest1() {
		StringReader reader = StrUtil.getReader("aaa,b\"bba\",ccc");
		CsvParser parser = new CsvParser(reader, null);
		CsvRow row = parser.nextRow();
		//noinspection ConstantConditions
		Assert.assertEquals("b\"bba\"", row.getRawList().get(1));
		IoUtil.close(parser);
	}
	
	@Test
	public void parseTest2() {
		StringReader reader = StrUtil.getReader("aaa,\"bba\"bbb,ccc");
		CsvParser parser = new CsvParser(reader, null);
		CsvRow row = parser.nextRow();
		//noinspection ConstantConditions
		Assert.assertEquals("\"bba\"bbb", row.getRawList().get(1));
		IoUtil.close(parser);
	}
	
	@Test
	public void parseTest3() {
		StringReader reader = StrUtil.getReader("aaa,\"bba\",ccc");
		CsvParser parser = new CsvParser(reader, null);
		CsvRow row = parser.nextRow();
		//noinspection ConstantConditions
		Assert.assertEquals("bba", row.getRawList().get(1));
		IoUtil.close(parser);
	}
	
	@Test
	public void parseTest4() {
		StringReader reader = StrUtil.getReader("aaa,\"\",ccc");
		CsvParser parser = new CsvParser(reader, null);
		CsvRow row = parser.nextRow();
		//noinspection ConstantConditions
		Assert.assertEquals("", row.getRawList().get(1));
		IoUtil.close(parser);
	}
}
