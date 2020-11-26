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
package io.github.jinlongliao.easy.dynamic.db.core.util;


import io.github.jinlongliao.easy.dynamic.db.core.annotation.Order;

import java.util.*;

/**
 * @author liaojinlong
 * @since 2020/9/14 18:33
 */
public class ServiceLoaderUtils {
  public synchronized static <T> List<T> loader(Class<T> tClass, ClassLoader classLoader) {
    if (Objects.isNull(classLoader)) {
      classLoader = Thread.currentThread().getContextClassLoader();
    }
    ServiceLoader<T> loader = ServiceLoader.load(tClass, classLoader);
    final Iterator<T> iterator = loader.iterator();
    List<T> result = new ArrayList<>(16);
    List<T> order = new ArrayList<>(16);
    while (iterator.hasNext()) {
      final T item = iterator.next();
      final boolean present = item.getClass().isAnnotationPresent(Order.class);
      if (present) {
        order.add(item);
      } else {
        result.add(item);
      }
    }
    if (order.size() > 0) {
      order.sort(Comparator.comparingInt(a -> a.getClass().getAnnotation(Order.class).value()));
      result.addAll(order);
      order.clear();
    }
    return result;
  }
}
