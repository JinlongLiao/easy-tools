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
package io.github.jinlonghliao.common.core.lang.caller;

import java.io.Serializable;

import io.github.jinlonghliao.common.core.exceptions.UtilException;

/**
 * 通过StackTrace方式获取调用者。此方式效率最低，不推荐使用
 * 
 * @author Looly
 */
public class StackTraceCaller implements Caller, Serializable {
	private static final long serialVersionUID = 1L;
	private static final int OFFSET = 2;

	@Override
	public Class<?> getCaller() {
		final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		if (OFFSET + 1 >= stackTrace.length) {
			return null;
		}
		final String className = stackTrace[OFFSET + 1].getClassName();
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new UtilException(e, "[{}] not found!", className);
		}
	}

	@Override
	public Class<?> getCallerCaller() {
		final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		if (OFFSET + 2 >= stackTrace.length) {
			return null;
		}
		final String className = stackTrace[OFFSET + 2].getClassName();
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new UtilException(e, "[{}] not found!", className);
		}
	}

	@Override
	public Class<?> getCaller(int depth) {
		final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		if (OFFSET + depth >= stackTrace.length) {
			return null;
		}
		final String className = stackTrace[OFFSET + depth].getClassName();
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new UtilException(e, "[{}] not found!", className);
		}
	}

	@Override
	public boolean isCalledBy(Class<?> clazz) {
		final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		for (final StackTraceElement element : stackTrace) {
			if (element.getClassName().equals(clazz.getName())) {
				return true;
			}
		}
		return false;
	}
}
