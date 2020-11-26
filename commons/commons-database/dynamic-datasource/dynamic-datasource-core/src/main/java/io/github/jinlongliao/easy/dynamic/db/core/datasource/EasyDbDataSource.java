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
package  io.github.jinlongliao.easy.dynamic.db.core.datasource;

import io.github.jinlongliao.easy.dynamic.db.core.config.ThreadConfig;
import io.github.jinlongliao.easy.dynamic.db.core.connection.WrapConnection;
import io.github.jinlongliao.easy.dynamic.db.core.constant.KeyConstant;
import io.github.jinlongliao.easy.dynamic.db.core.listener.datasource.DataSourceListener;
import io.github.jinlongliao.easy.dynamic.db.core.util.ServiceLoaderUtils;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author liaojinlong
 * @since 2020/9/14 14:52
 */
public class EasyDbDataSource implements DataSource {
    protected Map<String, WrapDataSource> wrapDataSourceMap;

    public EasyDbDataSource(Map<String, WrapDataSource> wrapDataSourceMap) {
        final List<DataSourceListener> loader = ServiceLoaderUtils.loader(DataSourceListener.class, Thread.currentThread().getContextClassLoader());
        this.wrapDataSourceMap = wrapDataSourceMap;
        wrapDataSourceMap.forEach((key, value) -> {
            value.addAllDataSourceListener(loader);
        });
    }

    /**
     * 对应KEY 的 DataSource
     *
     * @return DataSource
     */
    private DataSource getDataSource() {
        final String dbKey = ThreadConfig.getInstance().getString(KeyConstant.DB_KEY);
        if (!wrapDataSourceMap.containsKey(dbKey) && !wrapDataSourceMap.containsKey(KeyConstant.DEFAULT)) {
            throw new RuntimeException("No DataSource Config");
        }
        final WrapDataSource wrapDataSource = wrapDataSourceMap.get(dbKey);
        return wrapDataSource == null ? wrapDataSourceMap.get(KeyConstant.DEFAULT) : wrapDataSource;
    }

    @Override
    public Connection getConnection() throws SQLException {
        final WrapConnection wrap = WrapConnection.wrap(getDataSource().getConnection());
        putConnectionToThread(wrap);
        return wrap;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        final WrapConnection wrap = WrapConnection.wrap(getDataSource().getConnection(username, password));
        putConnectionToThread(wrap);
        return wrap;
    }

    /**
     * 将Connection 存入线程
     *
     * @param wrap
     */
    private void putConnectionToThread(Connection wrap) {
        ThreadConfig.getInstance().setConnection(wrap);
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return getDataSource().unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return getDataSource().isWrapperFor(iface);
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return getDataSource().getLogWriter();
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        getDataSource().setLogWriter(out);
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        getDataSource().setLoginTimeout(seconds);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return getDataSource().getLoginTimeout();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return getDataSource().getParentLogger();
    }
}
