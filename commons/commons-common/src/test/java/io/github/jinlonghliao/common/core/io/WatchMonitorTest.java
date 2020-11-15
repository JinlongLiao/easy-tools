package io.github.jinlonghliao.common.core.io;

import java.nio.file.Path;
import java.nio.file.WatchEvent;

import io.github.jinlonghliao.common.core.io.watch.SimpleWatcher;
import io.github.jinlonghliao.common.core.io.watch.WatchMonitor;
import io.github.jinlonghliao.common.core.io.watch.Watcher;
import io.github.jinlonghliao.common.core.io.watch.watchers.DelayWatcher;
import io.github.jinlonghliao.common.core.lang.Console;

/**
 * 文件监听单元测试
 * 
 * @author Looly
 *
 */
public class WatchMonitorTest {

	public static void main(String[] args) {
		Watcher watcher = new SimpleWatcher(){
			@Override
			public void onCreate(WatchEvent<?> event, Path currentPath) {
				Object obj = event.context();
				Console.log("创建：{}-> {}", currentPath, obj);
			}

			@Override
			public void onModify(WatchEvent<?> event, Path currentPath) {
				Object obj = event.context();
				Console.log("修改：{}-> {}", currentPath, obj);
			}

			@Override
			public void onDelete(WatchEvent<?> event, Path currentPath) {
				Object obj = event.context();
				Console.log("删除：{}-> {}", currentPath, obj);
			}

			@Override
			public void onOverflow(WatchEvent<?> event, Path currentPath) {
				Object obj = event.context();
				Console.log("Overflow：{}-> {}", currentPath, obj);
			}
		};
		
		WatchMonitor monitor = WatchMonitor.createAll("d:/test/aaa.txt", new DelayWatcher(watcher, 500));
		
		monitor.setMaxDepth(0);
		monitor.start();
	}
	
	
}
