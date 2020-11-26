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
package io.github.jinlongliao.easy.dynamic.db.spring.config.ext.alibaba;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.config.ConfigFilter;
import com.alibaba.druid.filter.encoding.EncodingConvertFilter;
import com.alibaba.druid.filter.logging.CommonsLogFilter;
import com.alibaba.druid.filter.logging.Log4j2Filter;
import com.alibaba.druid.filter.logging.Log4jFilter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import io.github.jinlongliao.easy.dynamic.db.core.util.StringUtils;
import io.github.jinlongliao.easy.dynamic.db.spring.config.EasyDbDatasourceProperties;
import io.github.jinlongliao.easy.dynamic.db.spring.config.ext.EasyDbExtDatasource;

import javax.sql.DataSource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liaojinlong
 * @since 2020/9/24 11:38
 */
public class DruidEasyDbExtDatasource implements EasyDbExtDatasource {
  /**
   * 额外扩展 非SpringBoot 支持的 DruidDataSource
   *
   * @param properties
   * @return DruidDataSource
   * @throws SQLException
   */
  @Override
  public DataSource getDataSource(EasyDbDatasourceProperties.DataSourceProperties properties) throws Exception {
    final DruidDataSource druidDataSource = new DruidDataSource();
    druidDataSource.setUrl(properties.getUrl());
    druidDataSource.setUsername(properties.getUsername());
    druidDataSource.setName(properties.getName());
    druidDataSource.setPassword(properties.getPassword());
    druidDataSource.setDriver(DriverManager.getDriver(properties.getUrl()));
    final Map<String, Object> extProps = properties.getExtProps();
    if (extProps != null && extProps.size() > 0) {
      Map<String, Object> target = toConversionConfig(extProps);
      copyProperties(target, druidDataSource);
    }
    return druidDataSource;
  }

  private void copyProperties(Map<String, Object> target, DruidDataSource dataSource) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
    final Method[] methods = dataSource.getClass().getMethods();
    Map<String, List<Method>> methodsMap = new HashMap<>(16);
    for (Method method : methods) {
      List<Method> methodList = null;
      if (methodsMap.containsKey(method.getName())) {
        methodList = methodsMap.get(method.getName());
      } else {
        methodList = new ArrayList<>();
        methodsMap.put(method.getName(), methodList);
      }
      methodList.add(method);
    }
    for (String key : target.keySet()) {
      String methodName = "set" + StringUtils.toFirstUpper(key);
      final List<Method> methodList = methodsMap.get(methodName);
      if (methodList == null || methodList.size() < 1) {
        continue;
      }
      final Method method = methodList.get(0);
      Object o = target.get(key);
      if (o instanceof Integer) {
        final Parameter parameter = method.getParameters()[0];
        final String name = parameter.getType().getName();
        if (name.equals("long") || name.equals(Long.class.getName())) {
          o = ((Integer) o).longValue();
        }
      }
      method.invoke(dataSource, o);
    }
  }

  /**
   * @param extProps
   * @return Map<String, Object>
   */
  private Map<String, Object> toConversionConfig(Map<String, Object> extProps) {
    Map<String, Object> target = new HashMap<>(16);
    for (String key : extProps.keySet()) {
//      if (key.equals("filters")) {
//        final List<Filter> filters = new DruidFilterConfiguration().getFilters(extProps.get(key).toString().split(","));
//        extProps.put(key, filters);
//      }
      target.put(StringUtils.toCamelCase(key.replace('-', '_')), extProps.get(key));
    }
    return target;
  }

  /**
   * @author lihengming [89921218@qq.com]
   */
  private class DruidFilterConfiguration {

    private static final String FILTER_STAT_PREFIX = "stat";
    private static final String FILTER_CONFIG_PREFIX = "config";
    private static final String FILTER_ENCODING_PREFIX = "encoding";
    private static final String FILTER_SLF4J_PREFIX = "slf4j";
    private static final String FILTER_LOG4J_PREFIX = "log4j";
    private static final String FILTER_LOG4J2_PREFIX = "log4j2";
    private static final String FILTER_COMMONS_LOG_PREFIX = "commons-log";
    private static final String FILTER_WALL_PREFIX = "wall";

    public List<Filter> getFilters(String... filters) {
      List<Filter> filterList = new ArrayList<>(8);
      for (String filter : filters) {
        switch (filter) {
          case FILTER_STAT_PREFIX:
            filterList.add(statFilter());
            break;
          case FILTER_COMMONS_LOG_PREFIX:
            filterList.add(commonsLogFilter());
            break;
          case FILTER_CONFIG_PREFIX:
            filterList.add(configFilter());
            break;
          case FILTER_ENCODING_PREFIX:
            filterList.add(encodingConvertFilter());
            break;
          case FILTER_LOG4J2_PREFIX:
            filterList.add(log4j2Filter());
            break;
          case FILTER_LOG4J_PREFIX:
            filterList.add(log4jFilter());
            break;
          case FILTER_SLF4J_PREFIX:
            filterList.add(slf4jLogFilter());
            break;
          case FILTER_WALL_PREFIX:
            filterList.add(wallFilter(wallConfig()));
            break;

        }
      }
      return filterList;
    }


    private StatFilter statFilter() {
      return new StatFilter();
    }

    private ConfigFilter configFilter() {
      return new ConfigFilter();
    }


    private EncodingConvertFilter encodingConvertFilter() {
      return new EncodingConvertFilter();
    }


    private Slf4jLogFilter slf4jLogFilter() {
      return new Slf4jLogFilter();
    }


    private Log4jFilter log4jFilter() {
      return new Log4jFilter();
    }


    private Log4j2Filter log4j2Filter() {
      return new Log4j2Filter();
    }


    private CommonsLogFilter commonsLogFilter() {
      return new CommonsLogFilter();
    }

    private WallConfig wallConfig() {
      return new WallConfig();
    }


    private WallFilter wallFilter(WallConfig wallConfig) {
      WallFilter filter = new WallFilter();
      filter.setConfig(wallConfig);
      return filter;
    }


  }
}
