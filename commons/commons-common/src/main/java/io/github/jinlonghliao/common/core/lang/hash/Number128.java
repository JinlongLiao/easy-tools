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
 package io.github.jinlonghliao.common.core.lang.hash;

/**
 * 128位数字表示，分高位和低位
 *
 * @author hexiufeng
 * @since 5.2.5
 */
public class Number128 {

	private long lowValue;
	private long highValue;

	/**
	 * 构造
	 *
	 * @param lowValue  低位
	 * @param highValue 高位
	 */
	public Number128(long lowValue, long highValue) {
		this.lowValue = lowValue;
		this.highValue = highValue;
	}

	public long getLowValue() {
		return lowValue;
	}

	public long getHighValue() {
		return highValue;
	}

	public void setLowValue(long lowValue) {
		this.lowValue = lowValue;
	}

	public void setHighValue(long hiValue) {
		this.highValue = hiValue;
	}

	public long[] getLongArray() {
		return new long[]{lowValue, highValue};
	}
}
