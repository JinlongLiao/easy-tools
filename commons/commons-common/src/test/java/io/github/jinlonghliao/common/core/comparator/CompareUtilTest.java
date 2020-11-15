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
