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
package io.github.jinlonghliao.common.core.date.chinese;

/**
 * 天干地支类
 *
 * @author looly
 * @since 5.4.1
 */
public class GanZhi {

	private static final String[] GAN = new String[]{"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
	private static final String[] ZHI = new String[]{"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};

	/**
	 * 传入 月日的offset 传回干支, 0=甲子
	 *
	 * @param num 月日的offset
	 * @return 干支
	 */
	public static String cyclicalm(int num) {
		return (GAN[num % 10] + ZHI[num % 12]);
	}
}
