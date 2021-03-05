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
package io.github.jinlongliao.easy.dynamic.db.core.annotation;

import io.github.jinlongliao.easy.dynamic.db.core.interceptor.Callback;

import java.lang.annotation.*;

/**
 * DB 方法拦截器
 *
 * @author liaojinlong
 * @since 2020/9/14 14:43
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DbDataSource {
  /**
   * <pre>
   *     value ==dbName && dbName > value
   * </pre>
   *
   * @return dbName
   * @see {@link DbDataSource#dbKey() }
   */
  String value();

  /**
   * 保存的数据的线程领域
   *
   * @return Scope
   */
  Scope scope() default Scope.LOCAL;

  /**
   * 设置 dbKey
   *
   * @return dbKey
   */
  String dbKey() default "";

  /**
   * 设置 回调方法 ，覆盖系统内置的
   *
   * @return Class<? extends Callback>
   */
  Class<? extends Callback> dbCall() default Callback.class;

  enum Scope {
    LOCAL,
    ALL
  }
}

