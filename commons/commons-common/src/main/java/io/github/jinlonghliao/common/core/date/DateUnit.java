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
package io.github.jinlonghliao.common.core.date;

/**
 * 日期时间单位，每个单位都是以毫秒为基数
 * @author Looly
 *
 */
public enum DateUnit {
	/** 一毫秒 */
	MS(1), 
	/** 一秒的毫秒数 */
	SECOND(1000), 
	/**一分钟的毫秒数 */
	MINUTE(SECOND.getMillis() * 60),
	/**一小时的毫秒数 */
	HOUR(MINUTE.getMillis() * 60),
	/**一天的毫秒数 */
	DAY(HOUR.getMillis() * 24),
	/**一周的毫秒数 */
	WEEK(DAY.getMillis() * 7);
	
	private final long millis;
	DateUnit(long millis){
		this.millis = millis;
	}
	
	/**
	 * @return 单位对应的毫秒数
	 */
	public long getMillis(){
		return this.millis;
	}
}
