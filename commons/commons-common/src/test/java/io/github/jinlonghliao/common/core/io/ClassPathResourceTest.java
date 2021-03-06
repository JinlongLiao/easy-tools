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
 package io.github.jinlonghliao.common.core.io;

import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

import io.github.jinlonghliao.common.core.io.resource.ClassPathResource;
import io.github.jinlonghliao.common.core.util.StrUtil;

/**
 * ClassPath资源读取测试
 * @author Looly
 *
 */
public class ClassPathResourceTest {
	
	@Test
	public void readStringTest() throws IOException{
		ClassPathResource resource = new ClassPathResource("test.properties");
		String content = resource.readUtf8Str();
		Assert.assertTrue(StrUtil.isNotEmpty(content));
	}
	
	@Test
	public void readStringTest2() throws IOException{
		ClassPathResource resource = new ClassPathResource("/");
		String content = resource.readUtf8Str();
		Assert.assertTrue(StrUtil.isNotEmpty(content));
	}
	
	@Test
	public void readTest() throws IOException{
		ClassPathResource resource = new ClassPathResource("test.properties");
		Properties properties = new Properties();
		properties.load(resource.getStream());
		
		Assert.assertEquals("1", properties.get("a"));
		Assert.assertEquals("2", properties.get("b"));
	}
	
	@Test
	public void readFromJarTest() throws IOException{
		//测试读取junit的jar包下的LICENSE-junit.txt文件
		final ClassPathResource resource = new ClassPathResource("LICENSE-junit.txt");
		
		String result = resource.readUtf8Str();
		Assert.assertNotNull(result);
		
		//二次读取测试，用于测试关闭流对再次读取的影响
		result = resource.readUtf8Str();
		Assert.assertNotNull(result);
	}
	
	@Test
	public void getAbsTest() {
		final ClassPathResource resource = new ClassPathResource("LICENSE-junit.txt");
		String absPath = resource.getAbsolutePath();
		Assert.assertTrue(absPath.contains("LICENSE-junit.txt"));
	}
}
