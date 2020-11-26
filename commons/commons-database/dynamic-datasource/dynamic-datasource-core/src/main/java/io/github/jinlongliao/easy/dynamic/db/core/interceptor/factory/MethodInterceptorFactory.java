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
package  io.github.jinlongliao.easy.dynamic.db.core.interceptor.factory;

import io.github.jinlongliao.easy.dynamic.db.core.interceptor.MethodInterceptor;
import io.github.jinlongliao.easy.dynamic.db.core.util.ServiceLoaderUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liaojinlong
 * @since 2020/9/14 17:38
 */

public class MethodInterceptorFactory {
  private static MethodInterceptorFactory instance = null;
  private static Map<String, List<MethodInterceptor>> METHOD_INTERCEPTOR_CACHE = new ConcurrentHashMap<>(32);

  private MethodInterceptorFactory() {
    final List<MethodInterceptor> loader = ServiceLoaderUtils.loader(MethodInterceptor.class, this.getClass().getClassLoader());
    loader.forEach(item -> {
      final List<MethodInterceptor> interceptors = METHOD_INTERCEPTOR_CACHE.getOrDefault(item.getAnnotation(),
              new ArrayList<>(16));
      interceptors.add(item);
      METHOD_INTERCEPTOR_CACHE.putIfAbsent(item.getAnnotation().getName(), interceptors);
    });
  }

  public Map<String, List<MethodInterceptor>> getMethodInterceptorCache() {
    return METHOD_INTERCEPTOR_CACHE;
  }

  public <T extends Annotation> List<MethodInterceptor> getMethodInterceptorByAnnotation(Class<T> annotation) {
    return METHOD_INTERCEPTOR_CACHE.get(annotation.getName());
  }

  public Map<String, List<MethodInterceptor>> getMethodInterceptorByAnnotation(Method method) {
    Map<String, List<MethodInterceptor>> result = new HashMap<>(METHOD_INTERCEPTOR_CACHE.size());
    final Annotation[] annotations = method.getAnnotations();
    for (int i = 0; i < annotations.length; i++) {
      Annotation annotation = annotations[i];
      if (METHOD_INTERCEPTOR_CACHE.containsKey(annotation.annotationType().getName())) {
        result.put(annotation.annotationType().getName(), METHOD_INTERCEPTOR_CACHE.get(annotation.annotationType().getName()));
      }
    }

    return result;
  }

  public static MethodInterceptorFactory getInstance() {
    if (instance == null) {
      //双重检查加锁，只有在第一次实例化时，才启用同步机制，提高了性能。
      synchronized (MethodInterceptorFactory.class) {
        if (instance == null) {
          instance = new MethodInterceptorFactory();
        }
      }
    }
    return instance;
  }

}
