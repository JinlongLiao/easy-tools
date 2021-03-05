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
 package io.github.jinlonghliao.common.core.convert;

import org.junit.Assert;
import org.junit.Test;

import io.github.jinlonghliao.common.core.convert.Converter;
import io.github.jinlonghliao.common.core.convert.ConverterRegistry;

/**
 * ConverterRegistry 单元测试
 * @author Looly
 *
 */
public class ConverterRegistryTest {
	
	@Test
	public void getConverterTest() {
		Converter<Object> converter = ConverterRegistry.getInstance().getConverter(CharSequence.class, false);
		Assert.assertNotNull(converter);
	}
	
	@Test
	public void customTest(){
		int a = 454553;
		ConverterRegistry converterRegistry = ConverterRegistry.getInstance();
		
		CharSequence result = converterRegistry.convert(CharSequence.class, a);
		Assert.assertEquals("454553", result);
		
		//此处做为示例自定义CharSequence转换，因为Hutool中已经提供CharSequence转换，请尽量不要替换
		//替换可能引发关联转换异常（例如覆盖CharSequence转换会影响全局）
		converterRegistry.putCustom(CharSequence.class, CustomConverter.class);
		result = converterRegistry.convert(CharSequence.class, a);
		Assert.assertEquals("Custom: 454553", result);
	}
	
	public static class CustomConverter implements Converter<CharSequence>{
		@Override
		public CharSequence convert(Object value, CharSequence defaultValue) throws IllegalArgumentException {
			return "Custom: " + value.toString();
		}
	}
}
