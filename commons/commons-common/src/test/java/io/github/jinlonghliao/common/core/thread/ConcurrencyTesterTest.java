package io.github.jinlonghliao.common.core.thread;

import io.github.jinlonghliao.common.core.lang.Console;
import io.github.jinlonghliao.common.core.util.RandomUtil;
import org.junit.Ignore;
import org.junit.Test;

public class ConcurrencyTesterTest {

	@Test
	@Ignore
	public void concurrencyTesterTest() {
		ConcurrencyTester tester = ThreadUtil.concurrencyTest(100, () -> {
			long delay = RandomUtil.randomLong(100, 1000);
			ThreadUtil.sleep(delay);
			Console.log("{} test finished, delay: {}", Thread.currentThread().getName(), delay);
		});
		Console.log(tester.getInterval());
	}
}
