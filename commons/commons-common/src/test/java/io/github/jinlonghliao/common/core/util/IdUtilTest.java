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
package io.github.jinlonghliao.common.core.util;

import io.github.jinlonghliao.common.core.collection.ConcurrentHashSet;
import io.github.jinlonghliao.common.core.date.DateUtil;
import io.github.jinlonghliao.common.core.date.TimeInterval;
import io.github.jinlonghliao.common.core.exceptions.UtilException;
import io.github.jinlonghliao.common.core.lang.Console;
import io.github.jinlonghliao.common.core.lang.Snowflake;
import io.github.jinlonghliao.common.core.thread.ThreadUtil;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * {@link IdUtil} 单元测试
 * 
 * @author looly
 *
 */
public class IdUtilTest {

	@Test
	public void randomUUIDTest() {
		String simpleUUID = IdUtil.simpleUUID();
		Assert.assertEquals(32, simpleUUID.length());

		String randomUUID = IdUtil.randomUUID();
		Assert.assertEquals(36, randomUUID.length());
	}
	
	@Test
	public void fastUUIDTest() {
		String simpleUUID = IdUtil.fastSimpleUUID();
		Assert.assertEquals(32, simpleUUID.length());
		
		String randomUUID = IdUtil.fastUUID();
		Assert.assertEquals(36, randomUUID.length());
	}

	/**
	 * UUID的性能测试
	 */
	@Test
	@Ignore
	public void benchTest() {
		TimeInterval timer = DateUtil.timer();
		for (int i = 0; i < 1000000; i++) {
			IdUtil.simpleUUID();
		}
		Console.log(timer.interval());

		timer.restart();
		for (int i = 0; i < 1000000; i++) {
			//noinspection ResultOfMethodCallIgnored
			UUID.randomUUID().toString().replace("-", "");
		}
		Console.log(timer.interval());
	}
	
	@Test
	public void objectIdTest() {
		String id = IdUtil.objectId();
		Assert.assertEquals(24, id.length());
	}
	
	@Test
	public void createSnowflakeTest() {
		Snowflake snowflake = IdUtil.createSnowflake(1, 1);
		long id = snowflake.nextId();
		Assert.assertTrue(id > 0);
	}
	
	@Test
	public void snowflakeBenchTest() {
		final Set<Long> set = new ConcurrentHashSet<>();
		final Snowflake snowflake = IdUtil.createSnowflake(1, 1);
		
		//线程数
		int threadCount = 100;
		//每个线程生成的ID数
		final int idCountPerThread = 10000;
		final CountDownLatch latch = new CountDownLatch(threadCount);
		for(int i =0; i < threadCount; i++) {
			ThreadUtil.execute(() -> {
				for(int i1 = 0; i1 < idCountPerThread; i1++) {
					long id = snowflake.nextId();
					set.add(id);
//						Console.log("Add new id: {}", id);
				}
				latch.countDown();
			});
		}
		
		//等待全部线程结束
		try {
			latch.await();
		} catch (InterruptedException e) {
			throw new UtilException(e);
		}
		Assert.assertEquals(threadCount * idCountPerThread, set.size());
	}
	
	@Test
	public void snowflakeBenchTest2() {
		final Set<Long> set = new ConcurrentHashSet<>();
		
		//线程数
		int threadCount = 100;
		//每个线程生成的ID数
		final int idCountPerThread = 10000;
		final CountDownLatch latch = new CountDownLatch(threadCount);
		for(int i =0; i < threadCount; i++) {
			ThreadUtil.execute(() -> {
				for(int i1 = 0; i1 < idCountPerThread; i1++) {
					long id = IdUtil.getSnowflake(1, 1).nextId();
					set.add(id);
//						Console.log("Add new id: {}", id);
				}
				latch.countDown();
			});
		}
		
		//等待全部线程结束
		try {
			latch.await();
		} catch (InterruptedException e) {
			throw new UtilException(e);
		}
		Assert.assertEquals(threadCount * idCountPerThread, set.size());
	}
}
