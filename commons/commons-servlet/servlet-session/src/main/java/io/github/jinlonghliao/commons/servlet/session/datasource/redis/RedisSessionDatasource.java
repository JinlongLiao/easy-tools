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
package io.github.jinlonghliao.commons.servlet.session.datasource.redis;

import io.github.jinlonghliao.commons.servlet.session.config.SessionConfig;
import io.github.jinlonghliao.commons.servlet.session.config.key.SessionKey;
import io.github.jinlonghliao.commons.servlet.session.datasource.AbstractSessionDatasource;
import io.github.jinlonghliao.commons.servlet.session.datasource.redis.imp.DefaultRedisObject;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Objects;

/**
 * @author liaojinlong
 * @since 2020/10/10 12:49
 */
public class RedisSessionDatasource extends AbstractSessionDatasource {
    private RedisObject redisObject;
    private String SESSION_KEY;
    private String CT;
    private String MI;
    private String VALIDATE;
    private String ATTR;
    private boolean isNew = Boolean.TRUE;

    public RedisSessionDatasource() {
        this(null);
    }

    public RedisSessionDatasource(RedisObject redisObject) {
        this.redisObject = redisObject;
    }

    @Override
    public void setSessionConfig(SessionConfig sessionConfig) {
        super.setSessionConfig(sessionConfig);
        if (Objects.isNull(redisObject)) {
            this.redisObject = new DefaultRedisObject(sessionConfig);
        }
        final SessionKey sessionKey = sessionConfig.getSessionKey();
        this.SESSION_KEY = "SESSION." + sessionKey.getAppName();
        this.CT = SESSION_KEY + ".CT";
        this.MI = SESSION_KEY + ".MI";
        this.VALIDATE = SESSION_KEY + ".VALIDATE";
        this.ATTR = SESSION_KEY + ".ATTR";
    }

    @Override
    public long getCreationTime() {
        Object o = redisObject.get(CT);
        long ct;
        if (Objects.isNull(o)) {
            ct = System.currentTimeMillis();
            redisObject.set(CT, ct + "");
        } else {
            ct = Long.valueOf(String.valueOf(o));
        }
        return ct;
    }

    @Override
    public long getLastAccessedTime() {
        return 0;
    }

    @Override
    public void setMaxInactiveInterval(int interval) {
        redisObject.set(MI, interval + "");
    }

    @Override
    public int getMaxInactiveInterval() {
        Object o = redisObject.get(MI);
        int mi;
        if (Objects.isNull(o)) {
            mi = 30;
            redisObject.set(MI, mi + "");
        } else {
            mi = Integer.valueOf(String.valueOf(o));
        }
        return mi;
    }

    @Override
    public Object getAttribute(String name) {
        return redisObject.hGet(ATTR, name);
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return Collections.enumeration(redisObject.hKeys(ATTR));
    }

    @Override
    public void setAttribute(String name, Object value) {
        redisObject.hPut(ATTR, name, value);
    }

    @Override
    public void removeAttribute(String name) {
        redisObject.hDel(ATTR, name);
    }

    @Override
    public void invalidate() {

        redisObject.set(VALIDATE, true + "");
    }

    public boolean idInvalidate() {
        Object o = redisObject.get(VALIDATE);
        boolean validate;
        if (Objects.isNull(o)) {
            validate = Boolean.FALSE;
            redisObject.set(VALIDATE, false + "");
        } else {
            validate = Boolean.valueOf(String.valueOf(o));
        }
        return validate;
    }

    @Override
    public boolean isNew() {
        return !getAttributeNames().hasMoreElements();
    }
}
