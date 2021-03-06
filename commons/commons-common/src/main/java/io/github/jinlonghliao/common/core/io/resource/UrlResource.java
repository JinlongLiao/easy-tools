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
package io.github.jinlonghliao.common.core.io.resource;

import io.github.jinlonghliao.common.core.io.FileUtil;
import io.github.jinlonghliao.common.core.io.IORuntimeException;
import io.github.jinlonghliao.common.core.io.IoUtil;
import io.github.jinlonghliao.common.core.util.ObjectUtil;
import io.github.jinlonghliao.common.core.util.URLUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * URL资源访问类
 * @author Looly
 *
 */
public class UrlResource implements Resource, Serializable{
	private static final long serialVersionUID = 1L;

	protected URL url;
	protected String name;

	//-------------------------------------------------------------------------------------- Constructor start
	/**
	 * 构造
	 * @param url URL
	 */
	public UrlResource(URL url) {
		this(url, null);
	}

	/**
	 * 构造
	 * @param url URL，允许为空
	 * @param name 资源名称
	 */
	public UrlResource(URL url, String name) {
		this.url = url;
		this.name = ObjectUtil.defaultIfNull(name, (null != url) ? FileUtil.getName(url.getPath()) : null);
	}

	/**
	 * 构造
	 * @param file 文件路径
	 * @deprecated Please use {@link FileResource}
	 */
	@Deprecated
	public UrlResource(File file) {
		this.url = URLUtil.getURL(file);
	}
	//-------------------------------------------------------------------------------------- Constructor end

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public URL getUrl(){
		return this.url;
	}

	@Override
	public InputStream getStream() throws NoResourceException{
		if(null == this.url){
			throw new NoResourceException("FontUtils URL is null!");
		}
		return URLUtil.getStream(url);
	}

	/**
	 * 获得Reader
	 * @param charset 编码
	 * @return {@link BufferedReader}
	 * @since 3.0.1
	 */
	@Override
	public BufferedReader getReader(Charset charset){
		return URLUtil.getReader(this.url, charset);
	}

	//------------------------------------------------------------------------------- read
	@Override
	public String readStr(Charset charset) throws IORuntimeException{
		BufferedReader reader = null;
		try {
			reader = getReader(charset);
			return IoUtil.read(reader);
		} finally {
			IoUtil.close(reader);
		}
	}

	@Override
	public byte[] readBytes() throws IORuntimeException{
		InputStream in = null;
		try {
			in = getStream();
			return IoUtil.readBytes(in);
		} finally {
			IoUtil.close(in);
		}
	}

	/**
	 * 获得File
	 * @return {@link File}
	 */
	public File getFile(){
		return FileUtil.file(this.url);
	}

	/**
	 * 返回路径
	 * @return 返回URL路径
	 */
	@Override
	public String toString() {
		return (null == this.url) ? "null" : this.url.toString();
	}
}
