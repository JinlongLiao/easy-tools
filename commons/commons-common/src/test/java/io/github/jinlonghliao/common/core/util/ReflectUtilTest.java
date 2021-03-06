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
package io.github.jinlonghliao.common.core.util;

import io.github.jinlonghliao.common.core.lang.test.bean.ExamInfoDict;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射工具类单元测试
 *
 * @author Looly
 */
public class ReflectUtilTest {

    @Test
    public void getMethodsTest() {
        Method[] methods = ReflectUtil.getMethods(ExamInfoDict.class);
        Assert.assertEquals(22, methods.length);

        //过滤器测试
        methods = ReflectUtil.getMethods(ExamInfoDict.class, t -> Integer.class.equals(t.getReturnType()));

        Assert.assertEquals(4, methods.length);
        final Method method = methods[0];
        Assert.assertNotNull(method);

        //null过滤器测试
        methods = ReflectUtil.getMethods(ExamInfoDict.class, null);

        Assert.assertEquals(22, methods.length);
        final Method method2 = methods[0];
        Assert.assertNotNull(method2);
    }

    @Test
    public void getMethodTest() {
        Method method = ReflectUtil.getMethod(ExamInfoDict.class, "getId");
        Assert.assertEquals("getId", method.getName());
        Assert.assertEquals(0, method.getParameterTypes().length);

        method = ReflectUtil.getMethod(ExamInfoDict.class, "getId", Integer.class);
        Assert.assertEquals("getId", method.getName());
        Assert.assertEquals(1, method.getParameterTypes().length);
    }

    @Test
    public void getMethodIgnoreCaseTest() {
        Method method = ReflectUtil.getMethodIgnoreCase(ExamInfoDict.class, "getId");
        Assert.assertEquals("getId", method.getName());
        Assert.assertEquals(0, method.getParameterTypes().length);

        method = ReflectUtil.getMethodIgnoreCase(ExamInfoDict.class, "GetId");
        Assert.assertEquals("getId", method.getName());
        Assert.assertEquals(0, method.getParameterTypes().length);

        method = ReflectUtil.getMethodIgnoreCase(ExamInfoDict.class, "setanswerIs", Integer.class);
        Assert.assertEquals("setAnswerIs", method.getName());
        Assert.assertEquals(1, method.getParameterTypes().length);
    }

    @Test
    public void getFieldTest() {
        // 能够获取到父类字段
        Field privateField = ReflectUtil.getField(ClassUtilTest.TestSubClass.class, "privateField");
        Assert.assertNotNull(privateField);
    }

    @Test
    public void getFieldsTest() {
        // 能够获取到父类字段
        final Field[] fields = ReflectUtil.getFields(ClassUtilTest.TestSubClass.class);
        Assert.assertEquals(4, fields.length);
    }

    @Test
    public void setFieldTest() {
        TestClass testClass = new TestClass();
        ReflectUtil.setFieldValue(testClass, "a", "111");
        Assert.assertEquals(111, testClass.getA());
    }

    @Test
    public void invokeTest() {
        TestClass testClass = new TestClass();
        ReflectUtil.invoke(testClass, "setA", 10);
        Assert.assertEquals(10, testClass.getA());
    }

    @Test
    public void noneStaticInnerClassTest() {
        final TestAClass testAClass = ReflectUtil.newInstanceIfPossible(TestAClass.class);
        Assert.assertNotNull(testAClass);
        Assert.assertEquals(2, testAClass.getA());
    }

    static class TestClass {
        private int a;

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }
    }

    @SuppressWarnings("InnerClassMayBeStatic")
    class TestAClass {
        private int a = 2;

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }
    }
}
