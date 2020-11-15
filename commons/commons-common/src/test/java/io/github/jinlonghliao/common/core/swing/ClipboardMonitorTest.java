package io.github.jinlonghliao.common.core.swing;

import io.github.jinlonghliao.common.core.lang.Console;
import io.github.jinlonghliao.common.core.swing.clipboard.ClipboardUtil;
import org.junit.Ignore;
import org.junit.Test;

public class ClipboardMonitorTest {

	@Test
	@Ignore
	public void monitorTest() {
		// 第一个监听
		ClipboardUtil.listen((clipboard, contents) -> {
			Object object = ClipboardUtil.getStr(contents);
			Console.log("1# {}", object);
			return contents;
		}, false);
		
		// 第二个监听
		ClipboardUtil.listen((clipboard, contents) -> {
			Object object = ClipboardUtil.getStr(contents);
			Console.log("2# {}", object);
			return contents;
		});

	}
}
