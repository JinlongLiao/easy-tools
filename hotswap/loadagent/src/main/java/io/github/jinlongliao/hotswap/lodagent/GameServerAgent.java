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
package io.github.jinlongliao.hotswap.lodagent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态代理加载器
 *
 * @author liaojinlong
 * @since 2021/1/7 20:14
 */
public class GameServerAgent {


    public static final String SPLIT = ";";

    public static void agentmain(String args, Instrumentation inst) throws Exception {
        System.out.println("agent 启动成功,开发重定义对象....");
        if (args != null && args.trim().length() > 0) {
            String[] classFileNames = args.split(SPLIT);
            if (classFileNames.length > 1) {
                // ROOT 目录
                String rootPath = classFileNames[0];
                Map<String, File> fileMap = new HashMap<>(classFileNames.length);
                for (int i = 1; i < classFileNames.length; i++) {
                    String classFileName = classFileNames[i];
                    File file = new File(classFileName);
                    fileMap.put(getClassName(file, rootPath), file);
                }

                Class<?>[] allClass = inst.getAllLoadedClasses();
                for (Class<?> aClass : allClass) {
                    String className = aClass.getName();
                    if (fileMap.containsKey(className)) {
                        try {
                            byte[] bytes = fileToBytes(fileMap.get(className));
                            System.out.println(String.format("文件大小：%s Class：%s", bytes.length, className));
                            ClassDefinition classDefinition = new ClassDefinition(aClass, bytes);
                            inst.redefineClasses(classDefinition);
                        } catch (IOException e) {
                            System.out.println("热更新失败...." + e.getLocalizedMessage());
                        }
                    }
                }
            }
        }
        System.out.println("热更新成功....");
    }

    /**
     * 获取 ClassName
     *
     * @param file
     * @param rootPath
     * @return ClassName
     */
    private static String getClassName(File file, String rootPath) {
        String filePath = file.getPath();
        filePath = filePath.replace(getPath(rootPath), "");
        String className = filePath.split("\\.")[0];
        filePath = className.replaceAll(getSplit(File.separator), ".");
        return filePath;
    }

    private static String getPath(String path) {
        if (path.endsWith(File.separator)) {
            return path;
        }
        return path + File.separator;
    }

    /**
     * 文件转 字节数组
     *
     * @param file
     * @return /
     * @throws IOException
     */
    private static byte[] fileToBytes(File file) throws IOException {
        try (FileInputStream in = new FileInputStream(file)) {
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            in.close();
            return bytes;
        } catch (IOException e) {
            throw e;
        }
    }

    private static String getSplit(String separator) {
        if (separator.equals("\\")) {
            return "\\\\";
        }
        return separator;
    }
}
