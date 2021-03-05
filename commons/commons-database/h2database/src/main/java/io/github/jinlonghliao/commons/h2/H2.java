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
package io.github.jinlonghliao.commons.h2;


import io.github.jinlonghliao.commons.h2.db.DbWeb;
import io.github.jinlonghliao.common.collection.CollectionUtils;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class H2 {

    private static final Logger log = LoggerFactory.getLogger(H2.class);

    private DbWeb dbWeb;

    public H2(DbWeb dbWeb) {
        this.dbWeb = dbWeb;
    }

    /**
     * 启动 H2 数据库
     */
    public void startH2ServerMode() {
        try {
            if (dbWeb.getTcp().isEnable()) {
                int port = dbWeb.getTcp().getPort();
                List<String> args = CollectionUtils.newArrayList("-tcpPort", port + "");
                if (dbWeb.getTcp().isAllowOther()) {
                    args.add("-tcpAllowOthers");
                }
                String[] array = args.toArray(new String[args.size()]);
                Server tcpServer = Server.createTcpServer(array).start();
                if (tcpServer.isRunning(true)) {
                    log.info("Tcp H2 server was started and is running.");
                } else {
                    log.error("Could not start TCP H2 server.");
                }
            }
            if (dbWeb.getWeb().isEnable()) {
                int port = dbWeb.getWeb().getPort();
                List<String> args = CollectionUtils.newArrayList("-webPort", port + "");
                if (dbWeb.getWeb().isAllowOther()) {
                    args.add("-webAllowOthers");
                }
                String[] array = args.toArray(new String[args.size()]);
                Server webServer = Server.createWebServer(array).start();
                if (webServer.isRunning(true)) {
                    log.info("Web H2 server was started and is running.");
                } else {
                    log.error("Could not start Web H2 server.");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to start  H2 server: ", e);
        }
    }


}
