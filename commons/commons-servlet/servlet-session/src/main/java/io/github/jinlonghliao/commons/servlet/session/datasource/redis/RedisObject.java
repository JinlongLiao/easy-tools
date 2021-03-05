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
package io.github.jinlonghliao.commons.servlet.session.datasource.redis;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Redis
 *
 * @author liaojinlong
 * @since 2020/10/12 18:23
 */
public interface RedisObject {


    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @return true=设置成功；false=设置失败
     */
    default boolean expire(final String key, final long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return true=设置成功；false=设置失败
     */
    boolean expire(final String key, final long timeout, final TimeUnit unit);

    /**
     * 删除key
     *
     * @param key 键
     * @return 删除成功数目
     */
    Long del(final String... key);


    /**
     * 存入普通对象
     *
     * @param key   Redis键
     * @param value 值
     */
    void set(final String key, final Object value);

    // 存储普通对象操作

    /**
     * 存入普通对象
     *
     * @param key     键
     * @param value   值
     * @param timeout 有效期，单位秒
     */
    void set(final String key, final Object value, final long timeout);

    /**
     * 获取普通对象
     *
     * @param key 键
     * @return 对象
     */
    Object get(final String key);

    // 存储Hash操作

    /**
     * 往Hash中存入数据
     *
     * @param key   Redis键
     * @param hKey  Hash键
     * @param value 值
     */
    void hPut(final String key, final String hKey, final Object value);

    /**
     * 往Hash中Keys
     *
     * @param key Redis键
     * @return
     */
    List<String> hKeys(final String key);

    /**
     * 往Hash中删除数据
     *
     * @param key  Redis键
     * @param hKey Hash键
     */
    void hDel(final String key, final String... hKey);

    /**
     * 往Hash中存入多个数据
     *
     * @param key    Redis键
     * @param values Hash键值对
     */
    void hPutAll(final String key, final Map<String, Object> values);

    /**
     * 获取Hash中的数据
     *
     * @param key  Redis键
     * @param hKey Hash键
     * @return Hash中的对象
     */
    Object hGet(final String key, final String hKey);

    /**
     * 获取多个Hash中的数据
     *
     * @param key   Redis键
     * @param hKeys Hash键集合
     * @return Hash对象集合
     */
    List<Object> hMultiGet(final String key, final Collection<String> hKeys);

    // 存储Set相关操作

    /**
     * 往Set中存入数据
     *
     * @param key    Redis键
     * @param values 值
     * @return 存入的个数
     */
    long sSet(final String key, final Object... values);

    /**
     * 删除Set中的数据
     *
     * @param key    Redis键
     * @param values 值
     * @return 移除的个数
     */
    long sDel(final String key, final Object... values);

    // 存储List相关操作

    /**
     * 往List中存入数据
     *
     * @param key   Redis键
     * @param value 数据
     * @return 存入的个数
     */
    long lPush(final String key, final Object value);

    /**
     * 往List中存入多个数据
     *
     * @param key    Redis键
     * @param values 多个数据
     * @return 存入的个数
     */
    long lPushAll(final String key, final Collection<Object> values);

    /**
     * 往List中存入多个数据
     *
     * @param key    Redis键
     * @param values 多个数据
     * @return 存入的个数
     */
    long lPushAll(final String key, final Object... values);

    /**
     * 从List中获取begin到end之间的元素
     *
     * @param key   Redis键
     * @param start 开始位置
     * @param end   结束位置（start=0，end=-1表示获取全部元素）
     * @return List对象
     */
    List<Object> lGet(final String key, final int start, final int end);
}