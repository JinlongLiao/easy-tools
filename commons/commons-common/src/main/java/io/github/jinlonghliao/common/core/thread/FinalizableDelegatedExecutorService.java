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

import java.util.concurrent.ExecutorService;

/**
 * 保证ExecutorService在对象回收时正常结束
 *
 * @author loolly
 */
public class FinalizableDelegatedExecutorService extends DelegatedExecutorService {

	/**
	 * 构造
	 *
	 * @param executor {@link ExecutorService}
	 */
	FinalizableDelegatedExecutorService(ExecutorService executor) {
		super(executor);
	}

	@Override
	protected void finalize() {
		super.shutdown();
	}
}