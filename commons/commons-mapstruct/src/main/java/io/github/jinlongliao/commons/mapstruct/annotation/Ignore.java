package io.github.jinlongliao.commons.mapstruct.annotation;

import java.lang.annotation.*;

/**
 * 忽略属性
 *
 * @author liaojinlong
 * @since 2020/12/31 22:23
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Ignore {
    boolean value() default true;
}
