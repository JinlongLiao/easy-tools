package io.github.jinlonghliao.commons.mapstruct.annotation;


import java.lang.annotation.*;

/**
 * 映射数据源
 *
 * @author liaojinlong
 * @since 2020/11/21 23:38
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Mapping {
    /**
     * @return 映射源名称
     */
    String source() default "";

    /**
     * @return 映射对象赋值函数
     */
    String method() default "";

    /**
     * 针对非基本类型（String,byte,short,int,float,double,long,char）<br/>
     * 除外需要指定自定义 静态转换函数
     * eg:
     * <pre>
     *     public static Person person(Object obj){
     *         return (Person)obj;
     *     }
     * </pre>
     *
     * @return 数据强转函数名称
     */
    String methodName() default "";

    /**
     * @return Class Name
     * @see {@link io.github.jinlonghliao.commons.mapstruct.annotation.Mapping#methodName()}
     */
    String className() default "";
}
