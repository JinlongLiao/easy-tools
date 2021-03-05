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
package io.github.jinlonghliao.common.core.lang;

import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

public class ClassScanerTest {
	
	@Test
	@Ignore
	public void scanTest() {
		ClassScanner scaner = new ClassScanner("io.github.jinlonghliao.common.core.util", null);
		Set<Class<?>> set = scaner.scan();
		for (Class<?> clazz : set) {
			Console.log(clazz.getName());
		}
	}
}
