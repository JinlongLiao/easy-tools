package io.github.jinlongliao.hotswap.trigger;


import com.sun.tools.attach.VirtualMachine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.io.FileFilter;
import java.lang.management.ManagementFactory;
import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liaojinlong
 * @since 2021/1/7 19:06
 */
public class HotSwapHttpServletContext implements ServletContextListener {
    private static final Logger log = LoggerFactory.getLogger(HotSwapHttpServletContext.class);
    private static String PATCHER_DIR;
    private static String AGENT_PATH;
    private static ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(2);
    private boolean init = false;
    private static final FileFilter FILTER = new FileFilter() {
        @Override
        public boolean accept(File pathname) {
            return pathname.isDirectory() || pathname.getPath().endsWith(".class");
        }
    };
    private static Map<String, Long> fileStatusCache = new HashMap<>(18);
    private static String PID;
    private static final String SPILT = ";";
    private String period;
    private String interval;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        //这里为了方便测试，打印出来进程id
        PID = name.split("@")[0];
        String agentName = sce.getServletContext().getInitParameter("AGENT_NAME");
        this.period = sce.getServletContext().getInitParameter("PERIOD");
        this.interval = sce.getServletContext().getInitParameter("INTERVAL");
        if (period == null || period.trim().length() == 0) {
            this.period = "5";
        }
        if (this.interval == null || this.interval.trim().length() == 0) {
            this.interval = "5";
        }
        String path = sce.getServletContext().getInitParameter("PATH");
        if (path == null || (path = path.trim()).equals("")) {
            PATCHER_DIR = path;
        } else {
            String realPath = sce.getServletContext().getRealPath("/");
            if (realPath.endsWith(File.separator)) {
                PATCHER_DIR = realPath + "patch";
            } else {
                PATCHER_DIR = realPath + File.separator + "patch";
            }
        }
        AGENT_PATH = PATCHER_DIR + File.separator + agentName;
        startSchedule();
    }

    /**
     * 定时替换
     */
    private void startSchedule() {
        poolExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.info("JavaClass热更新");
                File patch = new File(PATCHER_DIR);
                if (patch.exists()) {
                    List<File> listFiles = getFiles(patch);
                    if (!init) {
                        for (File item : listFiles) {
                            fileStatusCache.put(item.getPath(), item.lastModified());
                        }
                        init = true;
                    } else {
                        Map<String, Long> temp = new HashMap<>(18);
                        for (File file : listFiles) {
                            String path = file.getPath();
                            long modified = file.lastModified();
                            boolean swap = (!fileStatusCache.containsKey(path)) || modified != fileStatusCache.get(path);
                            if (swap) {
                                temp.put(path, modified);
                            }
                        }
                        fileStatusCache.putAll(temp);
                        if (temp.size() > 0) {
                            try {
                                StringBuffer keys = new StringBuffer(PATCHER_DIR);
                                keys.append(SPILT);
                                for (String key : fileStatusCache.keySet()) {
                                    log.debug("热更新文件名 {}", key);
                                    keys.append(key);
                                    keys.append(SPILT);
                                }
                                String substring = keys.substring(0, keys.length() - 1);
                                //VirtualMachine是jdk中tool.jar里面的东西，所以要在pom.xml引用这个jar
                                VirtualMachine vm = VirtualMachine.attach(PID);
                                log.debug("Agent 链接成功");
                                // 这个路径是相对于被热更的服务的，也就是这个pid的服务，也可以使用绝对路径。
                                vm.loadAgent(AGENT_PATH, substring);
                                vm.detach();
                            } catch (Exception e) {
                                log.error("JavaClass热更新  失败", e);
                            }
                        } else {
                            log.info("不存在可用更新");
                        }
                    }


                } else {
                    log.info("补丁文件夹不存在");
                }
            }
        }, Long.parseLong(interval), Long.parseLong(period), TimeUnit.MINUTES);
    }

    /**
     * 获取Files
     *
     * @param patch
     * @return /
     */
    private List<File> getFiles(File patch) {
        List<File> files = new ArrayList<>(18);
        for (File file : patch.listFiles(FILTER)) {
            if (file.isDirectory()) {
                files.addAll(getFiles(file));
            } else {
                files.add(file);
            }
        }
        return files;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        poolExecutor.shutdown();
    }
}
