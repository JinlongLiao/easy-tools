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
