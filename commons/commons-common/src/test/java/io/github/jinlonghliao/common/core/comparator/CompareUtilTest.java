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

import io.github.jinlonghliao.common.core.collection.ListUtil;
import io.github.jinlonghliao.common.core.comparator.CompareUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CompareUtilTest {

	@Test
	public void compareTest(){
		int compare = CompareUtil.compare(null, "a", true);
		Assert.assertTrue(compare > 0);

		compare = CompareUtil.compare(null, "a", false);
		Assert.assertTrue(compare < 0);
	}

	@Test
	public void comparingPinyin() {
		List<String> list = ListUtil.toList("成都", "北京", "上海", "深圳");

		List<String> ascendingOrderResult = ListUtil.of("北京", "成都", "上海", "深圳");
		List<String> descendingOrderResult = ListUtil.of("深圳", "上海", "成都", "北京");

		// 正序
		list.sort(CompareUtil.comparingPinyin(e -> e));
		Assert.assertEquals(list, ascendingOrderResult);

		// 反序
		list.sort(CompareUtil.comparingPinyin(e -> e, true));
		Assert.assertEquals(list, descendingOrderResult);
	}
}
