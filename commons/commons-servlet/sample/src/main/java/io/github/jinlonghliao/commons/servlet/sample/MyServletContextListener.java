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
package io.github.jinlonghliao.commons.servlet.sample;

import io.github.jinlonghliao.commons.servlet.session.config.SessionConfig;
import io.github.jinlonghliao.commons.servlet.session.config.datasource.SessionDataSource;
import io.github.jinlonghliao.commons.servlet.session.config.key.SessionKey;
import io.github.jinlonghliao.commons.servlet.session.datasource.redis.RedisSessionDatasource;
import io.github.jinlonghliao.commons.servlet.session.filter.SessionFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.EnumSet;

@WebListener("默认")
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        final SessionKey demo = new SessionKey(RedisSessionDatasource.class.getName(), "Demo", "127.0.0.1", 8080);
        final SessionDataSource sessionDataSource = new SessionDataSource("127.0.0.1", 6379, "1", "", false);
        final SessionConfig sessionConfig = new SessionConfig(demo, sessionDataSource);
        final SessionFilter filter = new SessionFilter(sessionConfig);
        final FilterRegistration.Dynamic dynamic = sce.getServletContext().addFilter("dynamic", filter);
        dynamic.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
