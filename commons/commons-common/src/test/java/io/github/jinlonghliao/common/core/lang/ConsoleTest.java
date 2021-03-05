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

import org.junit.Ignore;
import org.junit.Test;

import io.github.jinlonghliao.common.core.thread.ThreadUtil;

/**
 * 控制台单元测试
 * @author Looly
 *
 */
public class ConsoleTest {
	
	@Test
	public void logTest(){
		Console.log();
		
		String[] a = {"abc", "bcd", "def"};
		Console.log(a);
		
		Console.log("This is Console log for {}.", "test");
	}

	@Test
	public void logTest2(){
		Console.log("a", "b", "c");
		Console.log((Object) "a", "b", "c");
	}
	
	@Test
	public void printTest(){
		String[] a = {"abc", "bcd", "def"};
		Console.print(a);
		
		Console.log("This is Console print for {}.", "test");
	}

	@Test
	public void printTest2(){
		Console.print("a", "b", "c");
		Console.print((Object) "a", "b", "c");
	}
	
	@Test
	public void errorTest(){
		Console.error();
		
		String[] a = {"abc", "bcd", "def"};
		Console.error(a);
		
		Console.error("This is Console error for {}.", "test");
	}

	@Test
	public void errorTest2(){
		Console.error("a", "b", "c");
		Console.error((Object) "a", "b", "c");
	}
	
	@Test
	@Ignore
	public void inputTest() {
		Console.log("Please input something: ");
		String input = Console.input();
		Console.log(input);
	}
	
	@Test
	@Ignore
	public void printProgressTest() {
		for(int i = 0; i < 100; i++) {
			Console.printProgress('#', 100, i / 100D);
			ThreadUtil.sleep(200);
		}
	}

}
