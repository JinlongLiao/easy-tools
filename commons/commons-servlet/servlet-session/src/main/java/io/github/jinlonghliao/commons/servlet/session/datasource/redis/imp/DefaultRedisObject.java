package io.github.jinlonghliao.commons.servlet.session.datasource.redis.imp;

import io.github.jinlonghliao.common.collection.CollectionUtils;
import io.github.jinlonghliao.common.core.util.RuntimeUtil;
import io.github.jinlonghliao.commons.servlet.session.config.SessionConfig;
import io.github.jinlonghliao.commons.servlet.session.config.datasource.SessionDataSource;
import io.github.jinlonghliao.commons.servlet.session.datasource.redis.RedisObject;
import io.github.jinlonghliao.commons.servlet.session.datasource.redis.encode.Serialize;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 默认实现
 *
 * @author liaojinlong
 * @since 2020/10/12 18:28
 */
public class DefaultRedisObject implements RedisObject {
    private final Serialize serialize;
    protected RedisClient redisClient;

    public DefaultRedisObject(SessionConfig sessionConfig) {
        this(sessionConfig, new Serialize.Default());
    }

    public DefaultRedisObject(SessionConfig sessionConfig, Serialize serialize) {
        final SessionDataSource sessionDataSource = sessionConfig.getSessionDataSource();
        RedisURI redisUri = RedisURI.Builder.redis(sessionDataSource.getHost())
                .withSsl(sessionDataSource.getUserSsl())
                .withPassword(sessionDataSource.getPwd())
                .withDatabase(Integer.parseInt(sessionDataSource.getDatabase()))
                .build();
        redisClient = RedisClient.create(redisUri);
        this.serialize = serialize;
        RuntimeUtil.addShutdownHook(() -> redisClient.shutdown());
    }

    @Override
    public boolean expire(String key, long timeout, TimeUnit unit) {
        final StatefulRedisConnection<String, String> connection = getConnection();
        final RedisFuture<Boolean> expire = getAsync(connection).expire(key, unit.toSeconds(timeout));
        return getValue(connection, expire);
    }


    @Override
    public Long del(String... key) {
        final StatefulRedisConnection<String, String> connection = getConnection();
        final RedisFuture<Long> expire = getAsync(connection).del(key);
        return getValue(connection, expire);
    }

    @Override
    public void set(String key, Object value) {
        final StatefulRedisConnection<String, String> connection = getConnection();
        getSync(connection).set(key, serialize.toSerialize(value));
        closeConnection(connection);
    }

    @Override
    public void set(String key, Object value, long timeout) {
        final StatefulRedisConnection<String, String> connection = getConnection();
        final RedisFuture<String> set = getAsync(connection).set(key, serialize.toSerialize(value));
        getValue(connection, set);
    }

    @Override
    public Object get(String key) {
        final StatefulRedisConnection<String, String> connection = getConnection();
        final String data = getSync(connection).get(key);
        closeConnection(connection);
        return data;
    }

    @Override
    public void hPut(String key, String hKey, Object value) {
        final StatefulRedisConnection<String, String> connection = getConnection();
        final String data = getSync(connection).hmset(key, CollectionUtils.newHashMap(hKey, serialize.toSerialize(value)));
        closeConnection(connection);
    }

    @Override
    public List<String> hKeys(String key) {
        final StatefulRedisConnection<String, String> connection = getConnection();
        final List<String> hkeys = connection.sync().hkeys(key);
        closeConnection(connection);
        return hkeys;
    }

    @Override
    public void hDel(String key, String... hKey) {
        final StatefulRedisConnection<String, String> connection = getConnection();
        connection.sync().hdel(key, hKey);
        closeConnection(connection);
    }

    @Override
    public void hPutAll(String key, Map<String, Object> values) {
        final StatefulRedisConnection<String, String> connection = getConnection();
        Map<String, String> values2 = new HashMap<>();
        values.forEach((k, v) -> {
            values2.put(k, serialize.toSerialize(v));
        });
        final String data = getSync(connection).hmset(key, values2);
        closeConnection(connection);
    }

    @Override
    public Object hGet(String key, String hKey) {
        final StatefulRedisConnection<String, String> connection = getConnection();
        final String hget = getSync(connection).hget(key, hKey);
        closeConnection(connection);
        return hget;
    }

    @Override
    public List<Object> hMultiGet(String key, Collection<String> hKeys) {
        final StatefulRedisConnection<String, String> connection = getConnection();
        List<Object> result = new ArrayList<>(hKeys.size());
        hKeys.forEach((item) -> {
            result.add(connection.sync().hget(key, item));
        });

        closeConnection(connection);
        return result;
    }

    @Override
    public long sSet(String key, Object... values) {
        final StatefulRedisConnection<String, String> connection = getConnection();
        final String[] strings = Arrays.stream(values).map(item -> serialize.toSerialize(item))
                .collect(Collectors.toList()).toArray(new String[0]);
        connection.sync().xadd(key, strings);
        closeConnection(connection);
        return values.length;
    }

    @Override
    public long sDel(String key, Object... values) {
        final StatefulRedisConnection<String, String> connection = getConnection();
        final String[] strings = Arrays.stream(values).map(item -> serialize.toSerialize(item))
                .collect(Collectors.toList()).toArray(new String[0]);
        final Long xdel = connection.sync().xdel(key, strings);
        closeConnection(connection);
        return xdel;
    }

    @Override
    public long lPush(String key, Object value) {
        final StatefulRedisConnection<String, String> connection = getConnection();
        final Long lpush = connection.sync().lpush(key, serialize.toSerialize(value));
        closeConnection(connection);
        return lpush;
    }

    @Override
    public long lPushAll(String key, Collection<Object> values) {
        final StatefulRedisConnection<String, String> connection = getConnection();
        final String[] strings = values.stream().map(item -> serialize.toSerialize(item))
                .collect(Collectors.toList()).toArray(new String[0]);
        final Long lpush = connection.sync().lpush(key, strings);
        closeConnection(connection);
        return lpush;
    }

    @Override
    public long lPushAll(String key, Object... values) {
        final StatefulRedisConnection<String, String> connection = getConnection();
        final String[] strings = Arrays.stream(values).map(item -> serialize.toSerialize(item))
                .collect(Collectors.toList()).toArray(new String[0]);
        final Long lpush = connection.sync().lpush(key, strings);
        closeConnection(connection);
        return lpush;
    }

    @Override
    public List<Object> lGet(String key, int start, int end) {
        return null;
    }

    /**
     * 获取Exception
     *
     * @param tRedisFuture
     * @param <T>
     * @return 获取
     * @throws Exception
     */
    private <T> T getValue(StatefulRedisConnection<String, String> connection, RedisFuture<T> tRedisFuture) {
        try {
            return tRedisFuture.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    /**
     * 获取Exception
     *
     * @param tRedisFuture
     * @param <T>
     * @return 获取
     * @throws Exception
     */
    private <T> T getValue(StatefulRedisConnection<String, String> connection, RedisFuture<T> tRedisFuture, long timeout) {
        try {
            return tRedisFuture.get(timeout, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    /**
     * 异步开启
     *
     * @param connection
     * @return RedisAsyncCommands
     */
    private RedisAsyncCommands<String, String> getAsync(StatefulRedisConnection<String, String> connection) {
        return connection.async();
    }

    /**
     * 挺步开启
     *
     * @param connection
     * @return RedisCommands
     */
    private RedisCommands<String, String> getSync(StatefulRedisConnection<String, String> connection) {
        return connection.sync();
    }

    /**
     * 开启连接
     *
     * @return RedisCommands
     */
    private StatefulRedisConnection<String, String> getConnection() {
        return redisClient.connect();
    }

    /**
     * 关闭连接
     */
    private void closeConnection(StatefulRedisConnection<String, String> connection) {
        if (connection != null && connection.isOpen()) {
            connection.close();
        }
    }
}
