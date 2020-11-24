package io.github.jinlonghliao.commons.mapstruct.core;


import io.github.jinlonghliao.commons.mapstruct.BeanCopierUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class ReflectTest {
    public static boolean warmup = false;
    public static final int SIZE = 100000;
    private Map<String, Object> dataMap = new HashMap<String, Object>() {{
        put("name", "liaojl");
        put("age", 26);
        put("birthday", new Date());
    }};
    private Object[] dataArray = new Object[]{"liaojl", 26, new Date()};
    private final IData2Object data2Object = BeanCopierUtils.getData2Object(Person.class);

    @Test
    public void test() throws Exception {
        testMapCustomize();
        testArrayCustomize();
        testReflect();
        warmup = true;
        testMapCustomize();
        testArrayCustomize();
        testReflect();
    }

    private void testArrayCustomize() {
        final long start = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            final Class<Person> personClass = Person.class;
            Map<String, Object> item = new HashMap<>(dataMap);
            final Person person = data2Object.toArrayConverter(dataArray);
        }
        final long end = System.currentTimeMillis();
        if (warmup)
            System.out.println("testArrayCustomize:" + (end - start));
    }

    private void testMapCustomize() {
        final long start = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            final Class<Person> personClass = Person.class;
            Map<String, Object> item = new HashMap<>(dataMap);
            final Person person = data2Object.toMapConverter(item);
        }
        final long end = System.currentTimeMillis();
        if (warmup)
            System.out.println("testMapCustomize:" + (end - start));
    }

    private void testReflect() throws Exception {
        final long start = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            final Class<Person> personClass = Person.class;
            Map<String, Object> item = new HashMap<>(dataMap);
            Object person = personClass.newInstance();
            final Field[] fields = personClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(Boolean.TRUE);
                field.set(person, item.get(field.getName()));
            }
        }
        final long end = System.currentTimeMillis();
        if (warmup)
            System.out.println("testReflect:" + (end - start));
    }
}

