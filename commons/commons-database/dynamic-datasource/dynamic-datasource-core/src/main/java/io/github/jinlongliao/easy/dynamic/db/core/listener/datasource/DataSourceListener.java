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
package io.github.jinlongliao.easy.dynamic.db.core.listener.datasource;

import io.github.jinlongliao.easy.dynamic.db.core.datasource.WrapDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author liaojinlong
 * @since 2020/9/14 15:47
 */
public interface DataSourceListener {
  /**
   * 用户名，密码 连接前
   *
   * @param username
   * @param password
   * @param wrapDataSource
   * @throws SQLException
   */
  default void beforeConnection(String username, String password, WrapDataSource wrapDataSource) throws SQLException {
  }

  /**
   * 连接前
   *
   * @param wrapDataSource
   * @throws SQLException
   */
  default void beforeConnection(WrapDataSource wrapDataSource)
    throws SQLException {
  }

  /**
   * 用户名，密码 连接后
   *
   * @param connection
   * @param username
   * @param password
   * @param wrapDataSource
   * @return
   * @throws SQLException
   */
  default Connection afterConnection(Connection connection, String username, String password, WrapDataSource wrapDataSource) throws SQLException {
    return connection;
  }

  /**
   * 连接后
   *
   * @param connection
   * @param wrapDataSource
   * @return
   * @throws SQLException
   */
  default Connection afterConnection(Connection connection, WrapDataSource wrapDataSource)
    throws SQLException {
    return connection;
  }

  /**
   * 是否有效
   *
   * @param dbKey 数据源KEY
   * @return 是否有效
   */
  boolean isEnable(String dbKey);
}
