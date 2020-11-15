package io.github.jinlonghliao.common.core.util;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import io.github.jinlonghliao.common.core.clone.CloneSupport;
import io.github.jinlonghliao.common.core.collection.CollUtil;

public class ObjectUtilTest {

	@Test
	public void cloneTest() {
		Obj obj = new Obj();
		Obj obj2 = ObjectUtil.clone(obj);
		Assert.assertEquals("OK", obj2.doSomeThing());
	}

	static class Obj extends CloneSupport<Obj> {
		public String doSomeThing() {
			return "OK";
		}
	}

	@Test
	public void toStringTest() {
		ArrayList<String> strings = CollUtil.newArrayList("1", "2");
		String result = ObjectUtil.toString(strings);
		Assert.assertEquals("[1, 2]", result);
	}
}
