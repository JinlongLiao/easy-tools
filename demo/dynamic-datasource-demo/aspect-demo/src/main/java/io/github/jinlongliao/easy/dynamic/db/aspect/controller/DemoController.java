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
package io.github.jinlongliao.easy.dynamic.db.aspect.controller;

import io.github.jinlongliao.easy.dynamic.db.core.annotation.DbDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liaojinlong
 * @since 2020/9/17 15:53
 */
@RestController
@RequestMapping("/demo")
public class DemoController {
  @Autowired
  private DataSource dataSource;

  @DbDataSource(value = "db1")
  @RequestMapping("/1")
  @ResponseBody
  public Map<String, String> test() throws SQLException {
    final DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
    final Map<String, String> map = new HashMap<>(2);
    map.put("URL", metaData.getURL());
    System.out.println(map);
    return map;
  }

  @DbDataSource(value = "db2")
  @RequestMapping("/2")
  @ResponseBody
  public Map<String, String> test2() throws SQLException {
    final DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
    final Map<String, String> map = new HashMap<>(2);
    map.put("URL", metaData.getURL());
    System.out.println(map);
    return map;
  }
}
