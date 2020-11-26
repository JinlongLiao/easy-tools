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

import io.github.jinlongliao.easy.dynamic.db.aspect.DataSourceAspect;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author liaojinlong
 * @since 2020/9/23 14:13
 */
@Configurable
public class EasyDbAutoConfiguration {
  @Bean
  public DataSourceAspect dataSourceAspect() {
    return new DataSourceAspect();
  }

  @Bean
  @ConfigurationProperties("easy.db")
  public EasyDbDatasourceProperties easydbdatasourceproperties() {
    return new EasyDbDatasourceProperties();
  }

}
