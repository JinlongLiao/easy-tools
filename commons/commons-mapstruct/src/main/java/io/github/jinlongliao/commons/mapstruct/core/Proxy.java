package io.github.jinlongliao.commons.mapstruct.core;

import io.github.jinlongliao.commons.mapstruct.annotation.Ignore;
import io.github.jinlongliao.commons.mapstruct.annotation.Mapping;
import io.github.jinlongliao.commons.mapstruct.core.constant.ParamType;
import io.github.jinlongliao.commons.mapstruct.exception.ConverterException;
import io.github.jinlongliao.commons.mapstruct.exception.ConverterNotFountException;
import io.github.jinlongliao.commons.mapstruct.utils.Objects;
import javassist.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * IData2Object 代理类实现
 *
 * @author liaojinlong
 * @since 2020/11/23 21:48
 */
public class Proxy {
    private static final Logger log = LoggerFactory.getLogger(Proxy.class);
    public static final String DOT = ".";
    /**
     * 数据值转换 函数名
     */
    private static final Map<Type, String> VALUE_CONVERTER;

    static {
        VALUE_CONVERTER = new ConcurrentHashMap<>(1 << 8);
        final Class<InnerCoreDataConverter> innerCoreDataConverterClass = InnerCoreDataConverter.class;
        final String className = innerCoreDataConverterClass.getName();
        final Method[] methods = innerCoreDataConverterClass.getDeclaredMethods();
        for (Method method : methods) {
            final Class<?> type = method.getReturnType();
            final String methodName = method.getName();
            VALUE_CONVERTER.put(type, className + DOT + methodName);
        }
    }

    private static ClassPool pool = ClassPool.getDefault();

    private static final String PROXY_SUFFIX = "Impl";
    private static final String INTERFACE_NAME = IData2Object.class.getName();

    static {
        ClassClassPath classPath = new ClassClassPath(IData2Object.class);
        pool.insertClassPath(classPath);
    }

    /**
     * 生成代理对象
     *
     * @return io.github.jinlonghliao.commons.mapstruct.IData2Object
     */
    public IData2Object getProxyObject(Class tClass) {
        IData2Object proxyObject = null;
        //创建代理类对象
        CtClass ctClass = pool.makeClass(getProxyObjectName(tClass));
        try {
            //设置代理类的接口
            CtClass aClass = pool.getCtClass(INTERFACE_NAME);
            ctClass.setInterfaces(new CtClass[]{aClass});
            //代理类的所有方法
            CtMethod[] methods = aClass.getDeclaredMethods();
            for (CtMethod method : methods) {
                ParamType paramType;
                if ("toMapConverter".equals(method.getName())) {
                    paramType = ParamType.MAP;
                } else if ("toArrayConverter".equals(method.getName())) {
                    paramType = ParamType.ARRAY;
                } else if ("toHttpServletRequestConverter".equals(method.getName())) {
                    paramType = ParamType.SERVLET;
                } else {
                    throw new ConverterException("参数异常：");
                }
                buildToConverterMethod(paramType, method, ctClass, tClass);
            }
            proxyObject = (IData2Object) ctClass.toClass().newInstance();
        } catch (Exception e) {
            throw new ConverterException(e);
        }

        return proxyObject;
    }


    /**
     * 构建Map/Array/SERVLET 参数函数实现
     *
     * @param paramType
     * @param method
     * @param ctClass
     * @param tClass
     * @throws CannotCompileException
     * @throws NotFoundException
     */
    private void buildToConverterMethod(ParamType paramType, CtMethod method, CtClass ctClass, Class tClass) throws CannotCompileException, NotFoundException {
        String methodName = method.getName();
        CtMethod cm = new CtMethod(method.getReturnType(), methodName, method.getParameterTypes(), ctClass);
        final String methodBody = getMethodBodyStr(tClass, paramType);
        cm.setBody(methodBody);
        ctClass.addMethod(cm);
    }


    /**
     * 函数实现内容（Map/Array）
     *
     * @param tClass
     * @param paramType
     * @return 接口函数实现内容 （Map/Array）
     */
    private String getMethodBodyStr(Class tClass, ParamType paramType) {
        StringBuffer buffer = new StringBuffer("{");
        final Field[] fields = tClass.getDeclaredFields();
        final String objectName = tClass.getName();
        buffer.append(objectName + " tmp= new " + objectName + "();");
        int fieldIndex = 0;
        for (Field field : fields) {
            Ignore ignore = field.getAnnotation(Ignore.class);
            if (ignore != null && ignore.value()) {
                continue;
            }
            if (Modifier.isFinal(field.getModifiers())) {
                continue;
            }
            final Mapping mapping = field.getAnnotation(Mapping.class);
            final boolean nonNull = Objects.nonNull(mapping);
            final String setMethodName = nonNull
                    ? mapping.method().length() > 0
                    ? mapping.method()
                    : "set" + firstToUpper(field.getName())
                    : "set" + firstToUpper(field.getName());
            buffer.append("tmp.");
            buffer.append(setMethodName);
            buffer.append("(");
            buffer.append(getParamTypeConverterMethod(field, mapping));
            if (ParamType.MAP.equals(paramType)) {
                final String fieldName = nonNull
                        ? mapping.source().length() > 0
                        ? mapping.source()
                        : field.getName()
                        : field.getName();
                buffer.append("($1.get(\"");
                buffer.append(fieldName);
                buffer.append("\"))");
            } else if (ParamType.ARRAY.equals(paramType)) {
                buffer.append("($1[");
                buffer.append(fieldIndex);
                buffer.append("])");
            } else if (ParamType.SERVLET.equals(paramType)) {
                final String fieldName = nonNull
                        ? mapping.source().length() > 0
                        ? mapping.source()
                        : field.getName()
                        : field.getName();
                Class<?> type = field.getType();
                if (type == List.class) {
                    buffer.append("( java.util.Arrays.asList($1.getParameterValues(\"");
                    buffer.append(fieldName);
                    buffer.append("\")))");
                } else if (type.isArray()) {
                    buffer.append("($1.getParameterValues(\"");
                    buffer.append(fieldName);
                    buffer.append("\"))");
                } else {
                    buffer.append("($1.getParameter(\"");
                    buffer.append(fieldName);
                    buffer.append("\"))");
                }

            } else {
                throw new ConverterNotFountException(ParamType.class.getName() + "Not Found: " + paramType);
            }

            buffer.append(");");
            fieldIndex++;
        }

        buffer.append("return tmp;");
        buffer.append("}");
        return buffer.toString();
    }

    /**
     * 参数类型转换
     *
     * @param field
     * @return 赋值函数
     */
    private String getParamTypeConverterMethod(Field field, Mapping mapping) {
        final Class<?> type = field.getType();
        String converterMethod;
        final boolean exist = Objects.nonNull(mapping) &&
                mapping.className().trim().length() > 0 &&
                mapping.className().trim().length() > 0;
        if (exist) {
            converterMethod = mapping.className() + DOT + mapping.methodName();
        } else {
            String name = type.getName();
            if (VALUE_CONVERTER.containsKey(type)) {
                converterMethod = VALUE_CONVERTER.get(type);
            } else {
                throw new ConverterNotFountException("字段值转换类找不到");
            }
        }
        return converterMethod;
    }

    /**
     * 类名
     *
     * @param tClass
     * @return 实现类名
     */
    private String getProxyObjectName(Class tClass) {
        return tClass.getPackage().getName()
                + DOT
                + tClass.getSimpleName()
                + PROXY_SUFFIX
                + System.currentTimeMillis();
    }

    /**
     * 首字母大写
     *
     * @param str
     * @return 首字母大写
     */
    private String firstToUpper(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}