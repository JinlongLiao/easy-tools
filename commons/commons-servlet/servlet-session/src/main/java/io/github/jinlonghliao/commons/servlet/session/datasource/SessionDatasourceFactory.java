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
package io.github.jinlonghliao.commons.servlet.session.datasource;

import io.github.jinlonghliao.commons.servlet.session.config.SessionConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * @author liaojinlong
 * @since 2020/10/9 16:35
 */
public class SessionDatasourceFactory {
    private static Map<String, SessionDatasource> sessionDatasourceMap = new HashMap<>(16);

    private static SessionDatasourceFactory instance = null;
    private static SessionConfig sessionConfig;

    public static void setSessionConfig(SessionConfig sessionConfig) {
        SessionDatasourceFactory.sessionConfig = sessionConfig;
    }

    private SessionDatasourceFactory() {
    }

    public static SessionDatasourceFactory getInstance(io.github.jinlonghliao.commons.servlet.session.config.SessionConfig sessionConfig) {
        if (instance == null) {
            //双重检查加锁，只有在第一次实例化时，才启用同步机制，提高了性能。
            synchronized (SessionDatasourceFactory.class) {
                if (instance == null) {
                    instance = new SessionDatasourceFactory();
                    SessionDatasourceFactory.sessionConfig = sessionConfig;
                    init(sessionConfig);
                }
            }
        }
        return instance;
    }

    /**
     * @param sessionConfig
     */
    private static void init(SessionConfig sessionConfig) {
        final ServiceLoader<SessionDatasource> sessionDataSources = ServiceLoader.load(SessionDatasource.class);
        for (SessionDatasource sessionDataSource : sessionDataSources) {
            sessionDataSource.setSessionConfig(sessionConfig);
            sessionDatasourceMap.put(sessionDataSource.getId(), sessionDataSource);
        }
    }

    /**
     * 添加
     *
     * @param key
     * @param sessionDatasource
     */
    public void putSessionDatasource(String key, SessionDatasource sessionDatasource) {
        sessionDatasourceMap.put(key, sessionDatasource);
    }

    /**
     * 获取
     */
    public SessionDatasource getSessionDatasource() {
        return this.getSessionDatasource(SessionDatasourceFactory.sessionConfig.getSessionKey().getDataSourceName());
    }

    /**
     * 获取
     *
     * @param key
     */
    public SessionDatasource getSessionDatasource(String key) {
        return sessionDatasourceMap.get(key);
    }


}
