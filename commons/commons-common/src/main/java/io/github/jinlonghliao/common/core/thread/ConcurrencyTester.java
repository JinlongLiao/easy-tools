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
package io.github.jinlonghliao.common.core.thread;

import io.github.jinlonghliao.common.core.date.TimeInterval;

/**
 * 高并发测试工具类
 *
 * <pre>
 * ps:
 * //模拟1000个线程并发
 * ConcurrencyTester ct = new ConcurrencyTester(1000);
 * ct.test(() -&gt; {
 *      // 需要并发测试的业务代码
 * });
 * </pre>
 *
 * @author kwer
 */
public class ConcurrencyTester {
	private final SyncFinisher sf;
	private final TimeInterval timeInterval;
	private long interval;

	public ConcurrencyTester(int threadSize) {
		this.sf = new SyncFinisher(threadSize);
		this.timeInterval = new TimeInterval();
	}

	/**
	 * 执行测试
	 *
	 * @param runnable 要测试的内容
	 * @return this
	 */
	public ConcurrencyTester test(Runnable runnable) {
		timeInterval.start();
		this.sf//
				.addRepeatWorker(runnable)//
				.setBeginAtSameTime(true)// 同时开始
				.start();

		this.interval = timeInterval.interval();
		return this;
	}

	/**
	 * 获取执行时间
	 *
	 * @return 执行时间，单位毫秒
	 */
	public long getInterval() {
		return this.interval;
	}
}
