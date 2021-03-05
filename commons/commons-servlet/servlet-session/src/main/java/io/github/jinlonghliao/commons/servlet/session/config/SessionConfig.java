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
package io.github.jinlonghliao.commons.servlet.session.config;

import io.github.jinlonghliao.commons.servlet.session.config.datasource.SessionDataSource;
import io.github.jinlonghliao.commons.servlet.session.config.key.SessionKey;

/**
 * Session存储源 配置
 *
 * @author liaojinlong
 * @since 2020/10/12 17:51
 */
public class SessionConfig {
    private SessionKey sessionKey;
    private SessionDataSource sessionDataSource;

    public SessionConfig(SessionKey sessionKey, SessionDataSource sessionDataSource) {
        this.sessionKey = sessionKey;
        this.sessionDataSource = sessionDataSource;
    }

    public SessionKey getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(SessionKey sessionKey) {
        this.sessionKey = sessionKey;
    }

    public SessionDataSource getSessionDataSource() {
        return sessionDataSource;
    }

    public void setSessionDataSource(SessionDataSource sessionDataSource) {
        this.sessionDataSource = sessionDataSource;
    }
}
