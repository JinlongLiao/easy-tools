package io.github.jinlonghliao.commons.mapstruct.annotation;

import java.lang.annotation.*;

/**
 * 标识 此类为映射类，用于Spring 项目中自动映射
 *
 * @author liaojinlong
 * @since 2020/11/21 23:33
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Mapper {
    /**
     * bean Name
     *
     * @return Spring 中 bean Name
     */
    String value();
}
