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
 package io.github.jinlonghliao.common.core.io.resource;

import io.github.jinlonghliao.common.core.io.IORuntimeException;
import io.github.jinlonghliao.common.core.io.IoUtil;
import io.github.jinlonghliao.common.core.util.CharsetUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * 资源接口定义<br>
 * 资源可以是文件、URL、ClassPath中的文件亦或者jar包中的文件
 * 
 * @author looly
 * @since 3.2.1
 */
public interface Resource {
	
	/**
	 * 获取资源名，例如文件资源的资源名为文件名
	 * @return 资源名
	 * @since 4.0.13
	 */
	String getName();
	
	/**
	 * 获得解析后的{@link URL}
	 * @return 解析后的{@link URL}
	 */
	URL getUrl();
	
	/**
	 * 获得 {@link InputStream}
	 * @return {@link InputStream}
	 */
	InputStream getStream();

	/**
	 * 将资源内容写出到流，不关闭输出流，但是关闭资源流
	 * @param out 输出流
	 * @throws IORuntimeException IO异常
	 * @since 5.3.5
	 */
	default void writeTo(OutputStream out) throws IORuntimeException{
		try (InputStream in = getStream()) {
			IoUtil.copy(in, out);
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}
	
	/**
	 * 获得Reader
	 * @param charset 编码
	 * @return {@link BufferedReader}
	 */
	BufferedReader getReader(Charset charset);
	
	/**
	 * 读取资源内容，读取完毕后会关闭流<br>
	 * 关闭流并不影响下一次读取
	 * 
	 * @param charset 编码
	 * @return 读取资源内容
	 * @throws IORuntimeException 包装{@link IOException}
	 */
	String readStr(Charset charset) throws IORuntimeException;
	
	/**
	 * 读取资源内容，读取完毕后会关闭流<br>
	 * 关闭流并不影响下一次读取
	 * 
	 * @return 读取资源内容
	 * @throws IORuntimeException 包装IOException
	 */
	default String readUtf8Str() throws IORuntimeException{
		return readStr(CharsetUtil.CHARSET_UTF_8);
	}
	
	/**
	 * 读取资源内容，读取完毕后会关闭流<br>
	 * 关闭流并不影响下一次读取
	 * 
	 * @return 读取资源内容
	 * @throws IORuntimeException 包装IOException
	 */
	byte[] readBytes() throws IORuntimeException;
}
