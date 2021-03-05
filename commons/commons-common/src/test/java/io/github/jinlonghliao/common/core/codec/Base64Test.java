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
package io.github.jinlonghliao.common.core.codec;

import org.junit.Assert;
import org.junit.Test;

import io.github.jinlonghliao.common.core.util.CharsetUtil;
import io.github.jinlonghliao.common.core.util.StrUtil;

/**
 * Base64单元测试
 * 
 * @author looly
 *
 */
public class Base64Test {
	
	@Test
	public void encodeAndDecodeTest() {
		String a = "伦家是一个非常长的字符串66";
		String encode = Base64.encode(a);
		Assert.assertEquals("5Lym5a625piv5LiA5Liq6Z2e5bi46ZW/55qE5a2X56ym5LiyNjY=", encode);
		
		String decodeStr = Base64.decodeStr(encode);
		Assert.assertEquals(a, decodeStr);
	}

	@Test
	public void encodeAndDecodeTest2() {
		String a = "a61a5db5a67c01445ca2-HZ20181120172058/pdf/中国电信影像云单体网关Docker版-V1.2.pdf";
		String encode = Base64.encode(a, CharsetUtil.UTF_8);
		Assert.assertEquals("YTYxYTVkYjVhNjdjMDE0NDVjYTItSFoyMDE4MTEyMDE3MjA1OC9wZGYv5Lit5Zu955S15L+h5b2x5YOP5LqR5Y2V5L2T572R5YWzRG9ja2Vy54mILVYxLjIucGRm", encode);
		
		String decodeStr = Base64.decodeStr(encode, CharsetUtil.UTF_8);
		Assert.assertEquals(a, decodeStr);
	}

	@Test
	public void encodeAndDecodeTest3() {
		String a = ":";
		String encode = Base64.encode(a);
		Assert.assertEquals("Og==", encode);

		String decodeStr = Base64.decodeStr(encode);
		Assert.assertEquals(a, decodeStr);
	}
	
	@Test
	public void urlSafeEncodeAndDecodeTest() {
		String a = "广州伦家需要安全感55";
		String encode = StrUtil.utf8Str(Base64.encodeUrlSafe(StrUtil.utf8Bytes(a), false));
		Assert.assertEquals("5bm_5bee5Lym5a626ZyA6KaB5a6J5YWo5oSfNTU", encode);

		String decodeStr = Base64.decodeStr(encode);
		Assert.assertEquals(a, decodeStr);
	}
}
