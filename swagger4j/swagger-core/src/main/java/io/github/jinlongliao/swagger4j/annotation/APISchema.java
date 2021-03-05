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
package io.github.jinlongliao.swagger4j.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yonghaun
 * @since 1.0.0
 * @deprecated 在2.2.0中被废弃。
 */
@Deprecated
@Target(ElementType.PACKAGE)
@Retention(RetentionPolicy.RUNTIME)
public @interface APISchema {
    /**
     * 名称
     */
    String value();

    /**
     * 类型，默认是object
     */
    String type() default "object";

    /**
     * 属性集合
     */
    APISchemaPropertie[] properties();

    /**
     * 序列化为XML的根元素名
     */
    String xml();
}
