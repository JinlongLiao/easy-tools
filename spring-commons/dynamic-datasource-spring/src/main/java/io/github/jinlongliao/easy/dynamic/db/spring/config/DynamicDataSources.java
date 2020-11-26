package io.github.jinlongliao.easy.dynamic.db.spring.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author liaojinlong
 * @since 2020/11/26 23:25
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(DynamicDataSourceRegister.RepeatingRegistrar.class)
public @interface DynamicDataSources {
    DynamicDataSource[] value();
}
