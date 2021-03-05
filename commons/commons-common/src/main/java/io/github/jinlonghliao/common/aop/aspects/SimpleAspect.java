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
package io.github.jinlonghliao.common.aop.aspects;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * 简单切面类，不做任何操作<br>
 * 可以继承此类实现自己需要的方法即可
 *
 * @author Looly, ted.L
 */
public class SimpleAspect implements Aspect, Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public boolean before(Object target, Method method, Object[] args) {
		//继承此类后实现此方法
		return true;
	}


	/**
	 * 目标方法执行后的操作
	 * 如果 target.method 抛出异常且
	 *
	 * @param target 目标对象
	 * @param method 目标方法
	 * @param args   参数
	 * @return 是否允许返回值（接下来的操作）
	 * @see Aspect#afterException 返回true,则不会执行此操作
	 * 如果
	 * @see Aspect#afterException 返回false,则无论target.method是否抛出异常，均会执行此操作
	 */
	public boolean after(Object target, Method method, Object[] args) {
		//继承此类后实现此方法
		return after(target, method, args, null);
	}

	@Override
	public boolean after(Object target, Method method, Object[] args, Object returnVal) {
		//继承此类后实现此方法
		return true;
	}

	@Override
	public boolean afterException(Object target, Method method, Object[] args, Throwable e) {
		//继承此类后实现此方法
		return true;
	}

}
