/*
 *  Copyright 2018-2020 , 廖金龙 (mailto:jinlongliao@foxmail.com).
 * <p>
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package  io.github.jinlongliao.easy.dynamic.db.core.interceptor.datasource;

import io.github.jinlongliao.easy.dynamic.db.core.annotation.DbDataSource;
import io.github.jinlongliao.easy.dynamic.db.core.annotation.Order;
import io.github.jinlongliao.easy.dynamic.db.core.config.ThreadConfig;
import io.github.jinlongliao.easy.dynamic.db.core.constant.KeyConstant;
import io.github.jinlongliao.easy.dynamic.db.core.interceptor.AbstractMethodInterceptor;
import io.github.jinlongliao.easy.dynamic.db.core.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @author liaojinlong
 * @since 2020/9/14 17:05
 */
@Order(2 << 4)
public class DataSourceMethodInterceptor extends AbstractMethodInterceptor<DbDataSource, Class<DbDataSource>> {

    @Override
    public Class<DbDataSource> getAnnotation() {
        return DbDataSource.class;
    }

    @Override
    protected void before(DbDataSource annotation, Object proxy, Method method, Object[] args) {
        String dbKey = annotation.dbKey();
        if (StringUtils.isEmpty(dbKey)) {
            dbKey = annotation.value();
        }
        ThreadConfig.getInstance().put(KeyConstant.DB_KEY, dbKey);
        getCallback().before(annotation, proxy, method, args);
    }

    @Override
    protected Object doFinally(Object result, DbDataSource annotation, Object proxy, Method method, Object[] args) {
        result = getCallback().doFinally(result, annotation, proxy, method, args);
        ThreadConfig.getInstance().clear();
        return result;
    }
}
