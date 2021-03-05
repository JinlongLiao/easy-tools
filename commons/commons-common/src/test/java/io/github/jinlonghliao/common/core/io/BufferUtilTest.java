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

import java.nio.ByteBuffer;

import org.junit.Assert;
import org.junit.Test;

import io.github.jinlonghliao.common.core.util.CharsetUtil;
import io.github.jinlonghliao.common.core.util.StrUtil;

/**
 * BufferUtil单元测试
 * 
 * @author looly
 *
 */
public class BufferUtilTest {

	@Test
	public void copyTest() {
		byte[] bytes = "AAABBB".getBytes();
		ByteBuffer buffer = ByteBuffer.wrap(bytes);

		ByteBuffer buffer2 = BufferUtil.copy(buffer, ByteBuffer.allocate(5));
		Assert.assertEquals("AAABB", StrUtil.utf8Str(buffer2));
	}

	@Test
	public void readBytesTest() {
		byte[] bytes = "AAABBB".getBytes();
		ByteBuffer buffer = ByteBuffer.wrap(bytes);

		byte[] bs = BufferUtil.readBytes(buffer, 5);
		Assert.assertEquals("AAABB", StrUtil.utf8Str(bs));
	}

	@Test
	public void readBytes2Test() {
		byte[] bytes = "AAABBB".getBytes();
		ByteBuffer buffer = ByteBuffer.wrap(bytes);

		byte[] bs = BufferUtil.readBytes(buffer, 5);
		Assert.assertEquals("AAABB", StrUtil.utf8Str(bs));
	}

	@Test
	public void readLineTest() {
		String text = "aa\r\nbbb\ncc";
		ByteBuffer buffer = ByteBuffer.wrap(text.getBytes());

		// 第一行
		String line = BufferUtil.readLine(buffer, CharsetUtil.CHARSET_UTF_8);
		Assert.assertEquals("aa", line);

		// 第二行
		line = BufferUtil.readLine(buffer, CharsetUtil.CHARSET_UTF_8);
		Assert.assertEquals("bbb", line);

		// 第三行因为没有行结束标志，因此返回null
		line = BufferUtil.readLine(buffer, CharsetUtil.CHARSET_UTF_8);
		Assert.assertNull(line);

		// 读取剩余部分
		Assert.assertEquals("cc", StrUtil.utf8Str(BufferUtil.readBytes(buffer)));
	}
}
