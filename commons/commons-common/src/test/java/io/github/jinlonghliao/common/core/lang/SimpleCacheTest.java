/*
 * Copyright 2020-2021.
 * 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
 package io.github.jinlonghliao.common.core.lang;

import io.github.jinlonghliao.common.core.thread.ThreadUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SimpleCacheTest {

	@Before
	public void putTest(){
		final SimpleCache<String, String> cache = new SimpleCache<>();
		ThreadUtil.execute(()->cache.put("key1", "value1"));
		ThreadUtil.execute(()->cache.get("key1"));
		ThreadUtil.execute(()->cache.put("key2", "value2"));
		ThreadUtil.execute(()->cache.get("key2"));
		ThreadUtil.execute(()->cache.put("key3", "value3"));
		ThreadUtil.execute(()->cache.get("key3"));
		ThreadUtil.execute(()->cache.put("key4", "value4"));
		ThreadUtil.execute(()->cache.get("key4"));
		ThreadUtil.execute(()->cache.get("key5", ()->"value5"));

		cache.get("key5", ()->"value5");
	}

	@Test
	public void getTest(){
		final SimpleCache<String, String> cache = new SimpleCache<>();
		cache.put("key1", "value1");
		cache.get("key1");
		cache.put("key2", "value2");
		cache.get("key2");
		cache.put("key3", "value3");
		cache.get("key3");
		cache.put("key4", "value4");
		cache.get("key4");
		cache.get("key5", ()->"value5");

		Assert.assertEquals("value1", cache.get("key1"));
		Assert.assertEquals("value2", cache.get("key2"));
		Assert.assertEquals("value3", cache.get("key3"));
		Assert.assertEquals("value4", cache.get("key4"));
		Assert.assertEquals("value5", cache.get("key5"));
		Assert.assertEquals("value6", cache.get("key6", ()-> "value6"));
	}
}
