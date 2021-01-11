/*
 * Copyright 2020-2021.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.jinlongliao.swagger4j.annotation;

/**
 * @author yonghuan
 * @since 2.2.0
 */
public @interface Response {

    String statusCode() default "default";

    /**
     * Required. A short description of the response. GFM syntax can be used for rich text representation.
     */
    String description() default "";

    Class<?> schemaClass() default Void.class;

    /**
     * {@link #schemas()} 优于 {@link #schemaClass()}。
     */
    Schema[] schemas() default {};
}
