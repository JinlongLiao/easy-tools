package io.github.jinlonghliao.common.collection;

import java.util.*;

/**
 * @author liaojinlong
 * @since 2020/9/29 10:26
 */

public class CollectionUtils {
    public static final int SIZE = 16;

    /**
     * @param <T> 类型
     * @return List<T>
     */
    public static <T> List<T> newArrayList(T... ts) {
        return new ArrayList<>(Arrays.asList(ts));
    }

    /**
     * @param <T> 类型
     * @return List<T>
     */
    public static <T> List<T> newArrayList(Iterable<T> ts) {
        final List<T> list = newArrayList();
        ts.forEach(item -> list.add(item));
        return list;
    }

    /**
     * @param <T> 类型
     * @return List<T>
     */
    public static <T> List<T> newArrayList() {
        return newArrayList(SIZE);
    }

    /**
     * @param <T> 类型
     * @return List<T>
     */
    public static <T> List<T> newLinkList() {
        return new LinkedList<>();
    }

    /**
     * @param size 容积
     * @param <T>  类型
     * @return List<T>
     */
    public static <T> List<T> newArrayList(int size) {
        return new ArrayList<T>();
    }

    /**
     * Collection 是否为空
     *
     * @param collection
     * @return 是否为空
     */
    public static synchronized boolean isEmpty(Collection<?> collection) {
        if (collection != null && !collection.isEmpty()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * Map 是否为空
     *
     * @param map
     * @return 是否为空
     */
    public static synchronized boolean isEmpty(Map map) {
        if (map != null && !map.isEmpty()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 根据field数组中的定义，过滤掉不包含field[]中的数据。返回新Map，原map未改动
     *
     * @param fields
     * @param dataMap
     * @return Map<String, Object>
     */
    public static final Map<String, Object> filterMapByFields(String[] fields, final Map<String, Object> dataMap) {
        Map<String, Object> retMap = new HashMap<String, Object>();

        for (int i = 0; i < fields.length; i++) {
            String colName = fields[i];
            if (dataMap.containsKey(colName)) {
                retMap.put(colName, dataMap.get(colName));
            }
        }
        return retMap;
    }

    /**
     * HashMap
     *
     * @return HashMap
     */
    public static Map<String, Object> newHashMap() {
        return new HashMap<>(SIZE);
    }

    /**
     * HashMap
     *
     * @return HashMap
     */
    public static <K, V> Map<K, V> newHashMap(K key, V value) {
        final HashMap<K, V> map = new HashMap<>(SIZE);
        map.put(key, value);
        return map;
    }

    /**
     * HashMap
     *
     * @param rowNum
     * @return HashMap
     */
    public static Map<String, Object> newHashMap(int rowNum) {
        return new HashMap<>(rowNum);
    }
}
