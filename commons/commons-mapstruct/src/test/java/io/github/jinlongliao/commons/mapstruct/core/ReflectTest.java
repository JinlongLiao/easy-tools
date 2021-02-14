package io.github.jinlongliao.commons.mapstruct.core;


import io.github.jinlongliao.commons.mapstruct.BeanCopierUtils;
import io.github.jinlongliao.commons.mapstruct.annotation.Ignore;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * @author liaojinlong
 * @since 2020/11/24 23:03
 */

public class ReflectTest {
    public static boolean warmup = false;
    private Map<String, Object> data = new HashMap<>();

    private Map<String, Object> dataMap = new HashMap<String, Object>() {{
        put("grep", 1234);
        put("name", "liaojl");
        put("age", 26);
        put("birthday", new Date());
        put("arr", Arrays.asList("2312", "12423"));
        put("arr2", data);
        put("array", new int[]{1, 2, 3});
    }};
    private Object[] dataArray = new Object[]{13,"liaojl", 26, new Date(), Arrays.asList("2312", "12423"), data, new int[]{1, 2, 3}};
    private final IData2Object data2Object = BeanCopierUtils.getFullData2Object(Person.class);

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

    public static final int SIZE = 100000;

    private void testMapCustomize() {
        final long start = System.currentTimeMillis();
        Person person;
        for (int i = 0; i < SIZE; i++) {
            final Class<Person> personClass = Person.class;
            person = data2Object.toMapConverter(dataMap);
        }
        final long end = System.currentTimeMillis();
        if (warmup)
            System.out.println("testMapCustomize:" + (end - start));
    }

    private void testArrayCustomize() {
        final long start = System.currentTimeMillis();
        Person person = null;
        for (int i = 0; i < SIZE; i++) {
            person = data2Object.toArrayConverter(dataArray);
        }
        final long end = System.currentTimeMillis();
        if (warmup)
            System.out.println("testArrayCustomize:" + (end - start));
    }

    private void testReflect() throws Exception {
        final long start = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            final Class<Person> personClass = Person.class;
            Object person = personClass.newInstance();
            final Field[] fields = personClass.getDeclaredFields();
            for (Field field : fields) {
                if (Modifier.isFinal(field.getModifiers())) {
                    continue;
                }
                Ignore annotation = field.getAnnotation(Ignore.class);
                if (annotation != null) {
                    continue;
                }
                field.setAccessible(Boolean.TRUE);
                field.set(person, dataMap.get(field.getName()));
            }
        }
        final long end = System.currentTimeMillis();
        if (warmup)
            System.out.println("testReflect:" + (end - start));
    }
}

