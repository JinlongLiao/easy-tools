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

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

/**
 * Snowflake单元测试
 * @author Looly
 *
 */
public class SnowflakeTest {
	
	@Test
	public void snowflakeTest1(){
		//构建Snowflake，提供终端ID和数据中心ID
		Snowflake idWorker = new Snowflake(0, 0);
		long nextId = idWorker.nextId();
		Assert.assertTrue(nextId > 0);
	}
	
	@Test
	public void snowflakeTest(){
		HashSet<Long> hashSet = new HashSet<>();
		
		//构建Snowflake，提供终端ID和数据中心ID
		Snowflake idWorker = new Snowflake(0, 0);
		for (int i = 0; i < 1000; i++) {
			long id = idWorker.nextId();
			hashSet.add(id);
		}
		Assert.assertEquals(1000L, hashSet.size());
	}
	
	@Test
	public void snowflakeGetTest(){
		//构建Snowflake，提供终端ID和数据中心ID
		Snowflake idWorker = new Snowflake(1, 2);
		long nextId = idWorker.nextId();
		
		Assert.assertEquals(1, idWorker.getWorkerId(nextId));
		Assert.assertEquals(2, idWorker.getDataCenterId(nextId));
		Assert.assertTrue(idWorker.getGenerateDateTime(nextId) - System.currentTimeMillis() < 10);
	}
}
