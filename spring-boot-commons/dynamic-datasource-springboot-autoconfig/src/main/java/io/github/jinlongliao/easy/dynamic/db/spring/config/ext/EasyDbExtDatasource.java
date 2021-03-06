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
package io.github.jinlongliao.easy.dynamic.db.spring.config.ext;

import io.github.jinlongliao.easy.dynamic.db.spring.config.EasyDbDatasourceProperties;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author liaojinlong
 * @since 2020/9/24 11:26
 */
@FunctionalInterface
public interface EasyDbExtDatasource {
  /**
   * 额外扩展 非SpringBoot 支持的DataSource
   *
   * @param easyDbDatasourceProperties
   * @return DataSource
   * @throws Exception
   */
  DataSource getDataSource(EasyDbDatasourceProperties.DataSourceProperties easyDbDatasourceProperties) throws Exception;
}
