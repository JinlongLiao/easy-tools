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
package io.github.jinlonghliao.common.core.comparator;

import java.io.Serializable;
import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

/**
 * 按照GBK拼音顺序对给定的汉字字符串排序
 * 
 * @author looly
 * @since 4.0.8
 */
public class PinyinComparator implements Comparator<String>, Serializable {
	private static final long serialVersionUID = 1L;

	final Collator collator;

	/**
	 * 构造
	 */
	public PinyinComparator() {
		collator = Collator.getInstance(Locale.CHINESE);
	}

	@Override
	public int compare(String o1, String o2) {
		return collator.compare(o1, o2);
	}

}
