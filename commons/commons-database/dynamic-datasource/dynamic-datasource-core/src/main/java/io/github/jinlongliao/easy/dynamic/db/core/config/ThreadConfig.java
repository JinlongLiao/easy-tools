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
package  io.github.jinlongliao.easy.dynamic.db.core.config;


import io.github.jinlongliao.easy.dynamic.db.core.constant.KeyConstant;

import java.sql.Connection;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liaojinlong
 * @since 2020/9/14 14:56
 */
public class ThreadConfig implements Map<String, Object> {
  private static final ThreadLocal<Map<String, Object>> configAll = new InheritableThreadLocal() {
    @Override
    protected final Map<String, Object> initialValue() {
      return new ConcurrentHashMap<>(16);
    }
  };
  private static final ThreadLocal<Map<String, Object>> configSelf = new ThreadLocal() {
    @Override
    protected Map<String, Object> initialValue() {
      return new ConcurrentHashMap<>(16);
    }
  };
  private static ThreadConfig instance = null;

  /**
   * 子线程领域
   *
   * @return ThreadLocal<Map < String, Object>>
   */
  public ThreadLocal<Map<String, Object>> getConfigAll() {
    return configAll;
  }

  /**
   * 本线程领域
   *
   * @return ThreadLocal<Map < String, Object>>
   */
  public ThreadLocal<Map<String, Object>> getConfigSelf() {
    return configSelf;
  }

  /**
   * 本线程与子线程领域获取 ，本线程优先
   *
   * @param key
   * @return Object
   */
  public Object get(String key) {
    return configSelf.get().getOrDefault(key, configAll.get().get(key));
  }

  /**
   * 子线程领域获取
   *
   * @param key
   * @param inSubThread
   * @return Object
   */
  public Object get(String key, Boolean inSubThread) {
    return configAll.get().get(key);
  }

  /**
   * 本线程领域赋值
   *
   * @param key
   * @return Object
   */
  public void set(String key, Object value) {
    this.set(key, value, Boolean.FALSE);
  }

  /**
   * 线程领域赋值
   *
   * @param key
   * @param inSubThread
   * @return Object
   */
  public void set(String key, Object value, Boolean inSubThread) {
    configSelf.get().put(key, value);
    if (inSubThread) {
      configAll.get().put(key, value);
    }
  }

  /**
   * 本线程与子线程领域获取 Boolean ，本线程优先
   *
   * @param key
   * @return Boolean
   */
  public Boolean getBoolean(String key) {
    return Boolean.valueOf(String.valueOf(configSelf.get().getOrDefault(key, configAll.get().get(key))));
  }

  /**
   * 子线程领域获取 Boolean
   *
   * @param key
   * @return Boolean
   */
  public Boolean getBoolean(String key, Boolean inSubThread) {
    return Boolean.valueOf(String.valueOf(configAll.get().get(key)));
  }

  /**
   * 本线程领域 赋值
   *
   * @param key
   * @param value
   */
  public void setBoolean(String key, Boolean value) {
    this.setBoolean(key, value, Boolean.FALSE);
  }

  /**
   * 线程领域 赋值
   *
   * @param key
   * @param value
   * @param inSubThread
   */
  public void setBoolean(String key, Boolean value, Boolean inSubThread) {
    configSelf.get().put(key, value);
    if (inSubThread) {
      configAll.get().put(key, value);
    }
  }

  /**
   * 本线程与子线程领域获取 String ，本线程优先
   *
   * @param key
   * @return String
   */
  public String getString(String key) {
    return String.valueOf(configSelf.get().getOrDefault(key, configAll.get().get(key)));
  }

  /**
   * 子线程领域获取 String
   *
   * @param key
   * @param inSubThread
   * @return String
   */
  public String getString(String key, Boolean inSubThread) {
    return String.valueOf(configAll.get().get(key));
  }

  /**
   * 本线程领域 赋值String
   *
   * @param key
   * @param value
   */
  public void setString(String key, String value) {
    this.setString(key, value, Boolean.FALSE);
  }

  /**
   * 线程领域 赋值String
   *
   * @param key
   * @param value
   * @param inSubThread
   */
  public void setString(String key, String value, Boolean inSubThread) {
    configSelf.get().put(key, value);
    if (inSubThread) {
      configAll.get().put(key, value);
    }
  }

  /**
   * 本线程与子线程领域获取 Integer ，本线程优先
   *
   * @param key
   * @return Integer
   */
  public Integer getInteger(String key) {
    return (Integer) configSelf.get().getOrDefault(key, configAll.get().get(key));
  }

  /**
   * 子线程领域获取 Integer
   *
   * @param key
   * @param inSubThread
   * @return Integer
   */
  public Integer getInteger(String key, Integer inSubThread) {
    return (Integer) configAll.get().get(key);
  }

  /**
   * 本线程领域 赋值 Integer
   *
   * @param key
   * @param value
   */
  public void setInteger(String key, Integer value) {
    this.setInteger(key, value, Boolean.FALSE);
  }

  /**
   * 线程领域 赋值 Integer
   *
   * @param key
   * @param value
   * @param inSubThread
   */
  public void setInteger(String key, Integer value, Boolean inSubThread) {
    configSelf.get().put(key, value);
    if (inSubThread) {
      configAll.get().put(key, value);
    }
  }

  private ThreadConfig() {
  }

  /**
   * 单例
   *
   * @return ThreadConfig
   */
  public static ThreadConfig getInstance() {
    if (instance == null) {
      //双重检查加锁，只有在第一次实例化时，才启用同步机制，提高了性能。
      synchronized (ThreadConfig.class) {
        if (instance == null) {
          instance = new ThreadConfig();
        }
      }
    }
    return instance;
  }

  /**
   * 设置连接池的标识
   *
   * @param keyVal
   */
  public void setDbKey(String keyVal) {
    this.setDbKey(keyVal, false);
  }

  /**
   * 设置连接池的标识
   *
   * @param keyVal
   * @param inSubThread 是否在子线程可见
   */
  public void setDbKey(String keyVal, Boolean inSubThread) {
    this.put(KeyConstant.DB_KEY, keyVal);
    if (inSubThread) {
      configAll.get().put(KeyConstant.DB_KEY, keyVal);
    }
  }

  /**
   * 获取连接池的标识
   *
   * @return 获取连接池的标识
   */
  public String getDbKey() {
    return getDbKey(false);
  }

  /**
   * 获取连接池的标识
   *
   * @param inSubThread 设置获取子线程中
   * @return 获取连接池的标识
   */
  public String getDbKey(Boolean inSubThread) {
    return inSubThread ? (String) configAll.get().get(KeyConstant.DB_KEY) :
      (String) getOrDefault(KeyConstant.DB_KEY, configAll.get().get(KeyConstant.DB_KEY));
  }


  /**
   * 获取 Connection
   *
   * @return Connection
   */
  public Connection getConnection() {
    return getConnection(false);
  }

  /**
   * 获取 Connection
   *
   * @param inSubThread 设置获取子线程中
   * @return Connection
   */
  public Connection getConnection(boolean inSubThread) {
    return inSubThread ?
      (Connection) configAll.get().get(KeyConstant.CONNECTION)
      : (Connection) getOrDefault(KeyConstant.CONNECTION, configAll.get().get(KeyConstant.CONNECTION));
  }

  /**
   * 设置 Connection
   *
   * @return Connection
   */
  public void setConnection(Connection connection) {
    this.put(KeyConstant.CONNECTION, connection);
  }

  /**
   * 设置 Connection
   *
   * @param inSubThread 是否在子线程可见
   * @return Connection
   */
  public void setConnection(Connection connection, Boolean inSubThread) {
    this.put(KeyConstant.CONNECTION, connection);
    if (inSubThread) {
      configAll.get().put(KeyConstant.CONNECTION, connection);
    }
  }

  @Override
  public int size() {
    return configSelf.get().size();
  }

  @Override
  public boolean isEmpty() {
    return configSelf.get().isEmpty();
  }

  @Override
  public boolean containsKey(java.lang.Object key) {
    return configSelf.get().containsKey(key);
  }

  @Override
  public boolean containsValue(java.lang.Object value) {
    return configSelf.get().containsValue(value);
  }

  @Override
  public java.lang.Object get(java.lang.Object key) {
    return configSelf.get().get(key);
  }

  @Override
  public Object put(String key, Object value) {
    return configSelf.get().put(key, value);
  }


  @Override
  public java.lang.Object remove(java.lang.Object key) {
    Object remove = configAll.get().remove(key);
    if (configSelf.get().containsKey(key)) {
      remove = configSelf.get().remove(key);
    }
    return remove;
  }

  @Override
  public void putAll(Map m) {
    configSelf.get().putAll(m);
  }

  @Override
  public void clear() {
    this.clear(false);
  }

  public void clear(Boolean inSubThread) {
    configSelf.remove();
    if (inSubThread) {
      configAll.remove();
    }
  }

  @Override
  public Set<String> keySet() {
    final Set<String> strings = configSelf.get().keySet();
    strings.addAll(configAll.get().keySet());
    return strings;
  }

  @Override
  public Collection values() {
    final Collection<Object> values = configSelf.get().values();
    values.addAll(configAll.get().values());
    return values;
  }

  @Override
  public Set<Entry<String, Object>> entrySet() {
    final Set<Entry<String, Object>> entries = configSelf.get().entrySet();
    entries.addAll(configAll.get().entrySet());
    return entries;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    return this.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(configSelf.get(), configAll.get());
  }
}
