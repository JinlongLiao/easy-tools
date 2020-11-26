/*
 *  Copyright 2018-2020 , 廖金龙 (mailto:jinlongliao@foxmail.com).
 * <p>
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package  io.github.jinlongliao.easy.dynamic.db.simple.demo.mybatis.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author liaojinlong
 * @since 2020/9/21 17:26
 */
public class EasyDbSqlSessionFactory {
    private static File MYBATIS_CONFIG_PATH;
    private static SqlSessionFactory sqlSessionFactory;
    private static EasyDbSqlSessionFactory instance = null;

    private EasyDbSqlSessionFactory() {
    }

    public static EasyDbSqlSessionFactory getInstance() {
        if (instance == null) {
            //双重检查加锁，只有在第一次实例化时，才启用同步机制，提高了性能。
            synchronized (EasyDbSqlSessionFactory.class) {
                if (instance == null) {
                    instance = new EasyDbSqlSessionFactory();
                    final String mybatis_config_path = System.getProperty("MYBATIS_CONFIG_PATH");
                    if (mybatis_config_path == null) {
                        MYBATIS_CONFIG_PATH = new File(instance.getClass().getResource("/mybatis-config.xml").getPath());
                    } else {
                        MYBATIS_CONFIG_PATH = new File(mybatis_config_path);
                    }
                    final Path path = MYBATIS_CONFIG_PATH.toPath();
                    try (final InputStream inputStream = Files.newInputStream(path)) {
                        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
                    } catch (IOException e) {
                        throw new RuntimeException("Mybatis 配置文件不存在 " + MYBATIS_CONFIG_PATH.getAbsolutePath(), e);
                    }
                }
            }
        }
        return instance;
    }

    /**
     * SqlSessionFactory
     *
     * @return SqlSessionFactory
     */
    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
