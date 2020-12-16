package io.github.jinlonghliao.commons.mapstruct.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;


public class MapTest {
    public static final int SIZE = 10000000;
    public static final String KEY = "key";
    public static final Map<String, Object> treeMap = new TreeMap<String, Object>() {{
        put(KEY, "");
    }};
    public static final Map<String, Object> hashMap = new HashMap<String, Object>() {{
        put(KEY, "");
    }};
    public static final Map<String, Object> cronHashMap = new ConcurrentHashMap<String, Object>() {{
        put(KEY, "");
    }};

    @Test
    public void test() throws Exception {
        testTreeMap();
        testHashMap();
        testCronMap();
    }

    private void testCronMap() {
        final long start = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            cronHashMap.get(KEY);
            cronHashMap.put(KEY,"");
        }
        final long end = System.currentTimeMillis();
        System.out.println("testCronMap:" + (end - start));
    }

    private void testHashMap() {
        final long start = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            hashMap.get(KEY);
            hashMap.put(KEY,"");

        }
        final long end = System.currentTimeMillis();
        System.out.println("testHashMap:" + (end - start));
    }

    private void testTreeMap() {
        final long start = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            treeMap.get(KEY);
            treeMap.put(KEY,"");
        }
        final long end = System.currentTimeMillis();
        System.out.println("testTreeMap:" + (end - start));

    }
}
