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

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSONObject;
import io.github.jinlongliao.easy.dynamic.db.core.datasource.EasyDbDataSource;
import io.github.jinlongliao.easy.dynamic.db.core.datasource.WrapDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author liaojinlong
 * @since 2020/9/21 16:51
 */
public class MybatisEasyDbDataSourceFactory extends UnpooledDataSourceFactory {
    private static File DB_CONFIG_PATH;

    public MybatisEasyDbDataSourceFactory() {
        super();
        final String db_config_path = System.getProperty("DB_CONFIG_PATH");
        if (db_config_path == null) {
            DB_CONFIG_PATH = new File(this.getClass().getResource("/mybatis.config.json").getPath());
        } else {
            DB_CONFIG_PATH = new File(db_config_path);
        }
        try (final InputStream inputStream = Files.newInputStream(DB_CONFIG_PATH.toPath())) {
            String result = new BufferedReader(new InputStreamReader(inputStream))
                    .lines().collect(Collectors.joining(System.lineSeparator()));
            final Map<String, Map<String, String>> mybatisConfigs = JSONObject.parseObject(result, Map.class);
            Map<String, WrapDataSource> wrapDataSource = new HashMap<>();
            for (String key : mybatisConfigs.keySet()) {
                final MybatisConfig mybatisConfig = JSONObject.parseObject(JSONObject.toJSONString(mybatisConfigs.get(key)), MybatisConfig.class);
                DruidDataSource druidDataSource = new DruidDataSource();
                druidDataSource.setUrl(mybatisConfig.getUrl());
                druidDataSource.setUsername(mybatisConfig.getName());
                druidDataSource.setPassword(mybatisConfig.getPassword());
                druidDataSource.setDriverClassName(mybatisConfig.getDriverClassName());
                wrapDataSource.put(key, new WrapDataSource(druidDataSource, key, null));
            }
            this.dataSource = new EasyDbDataSource(wrapDataSource);
        } catch (IOException e) {
            throw new RuntimeException("Mybatis 配置文件不存在 " + DB_CONFIG_PATH.getAbsolutePath(), e);
        }
    }
}
