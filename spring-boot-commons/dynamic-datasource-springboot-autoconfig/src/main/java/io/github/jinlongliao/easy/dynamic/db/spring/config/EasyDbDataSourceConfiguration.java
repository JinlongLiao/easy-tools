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
package io.github.jinlongliao.easy.dynamic.db.spring.config;

import com.zaxxer.hikari.HikariDataSource;
import io.github.jinlongliao.easy.dynamic.db.core.datasource.EasyDbDataSource;
import io.github.jinlongliao.easy.dynamic.db.core.datasource.WrapDataSource;
import io.github.jinlongliao.easy.dynamic.db.spring.config.ext.EasyDbExtDatasource;
import io.github.jinlongliao.easy.dynamic.db.spring.config.ext.alibaba.DruidEasyDbExtDatasource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liaojinlong
 * @since 2020/9/17 14:35
 */
@Configuration(proxyBeanMethods = false)
public class EasyDbDataSourceConfiguration {

  protected static <T> T createDataSource(EasyDbDatasourceProperties.DataSourceProperties properties, Class<? extends DataSource> type) {
    return (T) properties.initializeDataSourceBuilder().type(type).build();
  }

  @Bean("dataSource")
  @ConditionalOnMissingBean(DataSource.class)
  EasyDbDataSource dataSourceTomcat(EasyDbDatasourceProperties easyDbDatasourceProperties) {
    Map<String, WrapDataSource> dataSources = new HashMap<>(4);
    for (String key : easyDbDatasourceProperties.getDatasource().keySet()) {
      final EasyDbDatasourceProperties.DataSourceProperties properties = easyDbDatasourceProperties.getDatasource().get(key);
      Class<? extends DataSource> type = properties.getType();
      if (type == null) {
        type = HikariDataSource.class;
      }
      final String typeName = type.getName();
      if (typeName.equals("org.apache.tomcat.jdbc.pool.DataSource")) {
        org.apache.tomcat.jdbc.pool.DataSource dataSource = createDataSource(properties,
          org.apache.tomcat.jdbc.pool.DataSource.class);
        DatabaseDriver databaseDriver = DatabaseDriver.fromJdbcUrl(properties.determineUrl());
        String validationQuery = databaseDriver.getValidationQuery();
        if (validationQuery != null) {
          dataSource.setTestOnBorrow(true);
          dataSource.setValidationQuery(validationQuery);
        }
        dataSources.put(key, new WrapDataSource(dataSource, key, null));
      } else if (typeName.equals("org.apache.commons.dbcp2.BasicDataSource")) {
        org.apache.commons.dbcp2.BasicDataSource dataSource = createDataSource(properties, org.apache.commons.dbcp2.BasicDataSource.class);
        dataSources.put(key, new WrapDataSource(dataSource, key, null));
      } else if (typeName.equals("com.zaxxer.hikari.HikariDataSource")) {
        HikariDataSource dataSource = createDataSource(properties, HikariDataSource.class);
        if (StringUtils.hasText(properties.getName())) {
          dataSource.setPoolName(properties.getName());
        }
        dataSources.put(key, new WrapDataSource(dataSource, key, null));
      } else if (typeName.equals("com.alibaba.druid.pool.DruidDataSource")) {
        final DruidEasyDbExtDatasource druidEasyDbExtDatasource = new DruidEasyDbExtDatasource();
        final DataSource dataSource;
        try {
          dataSource = druidEasyDbExtDatasource.getDataSource(properties);
          dataSources.put(key, new WrapDataSource(dataSource, key, null));
        } catch (Exception e) {
          throw new DatasourceInitException(e);
        }
      } else {
        final Class<? extends EasyDbExtDatasource> extType = properties.getExtType();
        if (extType == null) {
          throw new DatasourceInitException(properties);
        }
        try {
          final DataSource dataSource = extType.getConstructor(null).newInstance().getDataSource(properties);
          dataSources.put(key, new WrapDataSource(dataSource, key, null));
        } catch (Exception e) {
          throw new DatasourceInitException(e);
        }
      }
    }

    return new EasyDbDataSource(dataSources);
  }
}

class DatasourceInitException extends RuntimeException {
  public DatasourceInitException(EasyDbDatasourceProperties.DataSourceProperties properties) {
    super(properties.toString() + " 配置错误");
  }

  public DatasourceInitException(Exception e) {
    super(e);
  }
}
