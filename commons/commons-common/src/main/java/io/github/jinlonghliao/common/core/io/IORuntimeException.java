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

import io.github.jinlonghliao.common.core.exceptions.ExceptionUtil;
import io.github.jinlonghliao.common.core.util.StrUtil;

/**
 * IO运行时异常，常用于对IOException的包装
 * 
 * @author xiaoleilu
 */
public class IORuntimeException extends RuntimeException {
	private static final long serialVersionUID = 8247610319171014183L;

	public IORuntimeException(Throwable e) {
		super(ExceptionUtil.getMessage(e), e);
	}

	public IORuntimeException(String message) {
		super(message);
	}

	public IORuntimeException(String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params));
	}

	public IORuntimeException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public IORuntimeException(Throwable throwable, String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params), throwable);
	}

	/**
	 * 导致这个异常的异常是否是指定类型的异常
	 * 
	 * @param clazz 异常类
	 * @return 是否为指定类型异常
	 */
	public boolean causeInstanceOf(Class<? extends Throwable> clazz) {
		final Throwable cause = this.getCause();
		return null != clazz && clazz.isInstance(cause);
	}
}
