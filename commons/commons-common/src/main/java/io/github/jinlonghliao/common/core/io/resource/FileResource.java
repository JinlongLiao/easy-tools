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

import java.io.File;
import java.nio.file.Path;

import io.github.jinlonghliao.common.core.io.FileUtil;
import io.github.jinlonghliao.common.core.util.StrUtil;
import io.github.jinlonghliao.common.core.util.URLUtil;

/**
 * 文件资源访问对象
 * 
 * @author looly
 *
 */
public class FileResource extends UrlResource {
	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------- Constructor start
	/**
	 * 构造
	 * 
	 * @param path 文件
	 * @since 4.4.1
	 */
	public FileResource(Path path) {
		this(path.toFile());
	}

	/**
	 * 构造
	 * 
	 * @param file 文件
	 */
	public FileResource(File file) {
		this(file, file.getName());
	}

	/**
	 * 构造
	 * 
	 * @param file 文件
	 * @param fileName 文件名，如果为null获取文件本身的文件名
	 */
	public FileResource(File file, String fileName) {
		super(URLUtil.getURL(file), StrUtil.isBlank(fileName) ? file.getName() : fileName);
	}

	/**
	 * 构造
	 * 
	 * @param path 文件绝对路径或相对ClassPath路径，但是这个路径不能指向一个jar包中的文件
	 */
	public FileResource(String path) {
		this(FileUtil.file(path));
	}
	// ----------------------------------------------------------------------- Constructor end

}
