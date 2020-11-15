package io.github.jinlonghliao.common.core.swing;

import org.junit.Ignore;
import org.junit.Test;

import io.github.jinlonghliao.common.core.io.FileUtil;

public class RobotUtilTest {

	@Test
	@Ignore
	public void captureScreenTest() {
		RobotUtil.captureScreen(FileUtil.file("e:/screen.jpg"));
	}
}
