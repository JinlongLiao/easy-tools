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

import io.github.jinlongliao.easy.dynamic.db.core.listener.datasource.DataSourceListener;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * @author liaojinlong
 * @since 2020/9/14 15:14
 */
public class WrapDataSource implements DataSource {
    private DataSource dataSource;
    private String dbKey;
    private final Boolean listenerEnable;
    private List<DataSourceListener> dataSourceListeners = new ArrayList<>(16);

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setDbKey(String dbKey) {
        this.dbKey = dbKey;
    }

    public void addDataSourceListener(DataSourceListener dataSourceListener) {
        if (dataSourceListener.isEnable(this.dbKey)) {
            dataSourceListeners.add(dataSourceListener);
        }
    }

    public void addAllDataSourceListener(List<DataSourceListener> dataSourceListener) {
        dataSourceListener.forEach(item -> this.addDataSourceListener(item));
    }


    /**
     * 包装类
     *
     * @param dataSource         DataSource
     * @param dbKey              key
     * @param dataSourceListener 监听器
     */
    public WrapDataSource(DataSource dataSource,
                          String dbKey,
                          List<DataSourceListener> dataSourceListener) {
        this.dataSource = dataSource;
        this.dbKey = dbKey;
        listenerEnable = Objects.nonNull(dataSourceListener);
        if (listenerEnable) {
            this.dataSourceListeners.addAll(dataSourceListener);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        Connection connection;
        if (listenerEnable) {
            for (DataSourceListener dataSourceListener : dataSourceListeners) {
                dataSourceListener.beforeConnection(this);
            }
            connection = dataSource.getConnection();
            for (DataSourceListener dataSourceListener : dataSourceListeners) {
                connection = dataSourceListener.afterConnection(connection, this);
            }
        } else {
            connection = dataSource.getConnection();
        }
        return connection;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        Connection connection;
        if (listenerEnable) {
            for (DataSourceListener dataSourceListener : dataSourceListeners) {
                dataSourceListener.beforeConnection(username, password, this);
            }
            connection = dataSource.getConnection(username, password);
            for (DataSourceListener dataSourceListener : dataSourceListeners) {
                connection = dataSourceListener.afterConnection(connection, username, password, this);
            }
        } else {
            connection = dataSource.getConnection(username, password);
        }
        return connection;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return dataSource.unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return dataSource.isWrapperFor(iface);
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return dataSource.getLogWriter();
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        dataSource.setLogWriter(out);
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        dataSource.setLoginTimeout(seconds);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return dataSource.getLoginTimeout();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return dataSource.getParentLogger();
    }

    /**
     * 此数据源的DbKey
     *
     * @return DbKey
     */
    public String getDbKey() {
        return dbKey;
    }
}
