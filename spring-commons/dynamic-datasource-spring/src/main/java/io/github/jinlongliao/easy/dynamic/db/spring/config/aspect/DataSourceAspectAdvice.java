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
package io.github.jinlongliao.easy.dynamic.db.spring.config.aspect;

import io.github.jinlongliao.easy.dynamic.db.core.config.ThreadConfig;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.Objects;

/**
 * 指定特定目录下使用某个数据源配置
 *
 * @author liaojinlong
 * @since 2020/11/26 23:14
 */
public class DataSourceAspectAdvice implements MethodInterceptor {
    private String dbKey;

    public DataSourceAspectAdvice(String dbKey) {
        this.dbKey = dbKey;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        final ThreadConfig threadConfig = ThreadConfig.getInstance();
        final boolean notExistDbKey = !Objects.nonNull(threadConfig.getDbKey());
        try {
            if (notExistDbKey) {
                threadConfig.setDbKey(this.dbKey, true);
            }
            return invocation.proceed();
        } finally {
            if (notExistDbKey) {
                threadConfig.clear(true);
            }
        }
    }
}
