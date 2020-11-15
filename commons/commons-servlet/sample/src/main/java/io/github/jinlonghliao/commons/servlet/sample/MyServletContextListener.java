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
