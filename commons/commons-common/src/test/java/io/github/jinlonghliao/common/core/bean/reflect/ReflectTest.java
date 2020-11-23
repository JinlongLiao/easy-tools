package io.github.jinlonghliao.common.core.bean.reflect;

import io.github.jinlonghliao.common.core.bean.copier.BeanCopier;
import io.github.jinlonghliao.common.core.bean.copier.CopyOptions;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ReflectTest {
    private Map<String, Object> data = new HashMap<String, Object>() {{
        put("name", "liaojl");
        put("age", 26);
    }};

    @Test
    public void test() throws Exception {
        testBeanCopier();

        testReflect();
    }

    private void testReflect() throws Exception {
        final long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            final Class<Person> personClass = Person.class;
            Object person = personClass.newInstance();
            final Field[] fields = personClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(Boolean.TRUE);
                field.set(person, data.get(field.getName()));
            }
        }
        final long end = System.currentTimeMillis();
        System.out.println("testReflect:" + (end - start));
    }

    private void testBeanCopier() throws Exception {
        final long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            Map<String, Object> item = new HashMap<>(data);
            final Person person = Person.class.newInstance();
            final BeanCopier<Class<Person>> classBeanCopier = BeanCopier.create(item, Person.class, CopyOptions.create());
            classBeanCopier.copy();
        }
        final long end = System.currentTimeMillis();
        System.out.println("testBeanCopier:" + (end - start));
    }


    @Getter
    @Setter
    public class Person {
        private String name;
        private int age;
    }
}

