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
package  io.github.jinlongliao.easy.dynamic.db.aspect.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSONObject;
import io.github.jinlongliao.easy.dynamic.db.aspect.DataSourceAspect;
import io.github.jinlongliao.easy.dynamic.db.aspect.demo.datasource.MybatisConfig;
import io.github.jinlongliao.easy.dynamic.db.core.datasource.EasyDbDataSource;
import io.github.jinlongliao.easy.dynamic.db.core.datasource.WrapDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Eric Zhao
 */
@MapperScan(basePackages = {
  "io.github.jinlongliao.easy.dynamic.db.aspect.demo.mybatis.dao"
})
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = {"io.github.jinlongliao.easy.dynamic.db.aspect.demo"},
  excludeFilters = {
    @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)})

public class SpringConfig {
  private static File DB_CONFIG_PATH;
  private EasyDbDataSource dataSource;

  @Bean
  public DataSourceAspect dataSourceAspect() {
    return new DataSourceAspect();
  }

  @Bean
  public DataSource dataSource() {
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
      Map<String, WrapDataSource> wrapDataSource = new HashMap<>(8);
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
    return dataSource;
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    factoryBean.setDataSource(dataSource());
    factoryBean.setMapperLocations(MyWebApplicationInitializer
      .getApplicationContext()
      .getResources("classpath:mapper/*Dao.xml"));
    final SqlSessionFactory sqlSessionFactory = factoryBean.getObject();
    return sqlSessionFactory;
  }
}
