/*
 * Copyright 2020-2021.
 * 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
 package io.github.jinlonghliao.commons.h2;

import io.github.jinlonghliao.commons.h2.db.DbWeb;
import org.junit.Test;

import java.io.IOException;

public class H2Test {
    @Test
    public void testWeb() throws IOException {
        final DbWeb.H2Db web = new DbWeb.H2Db();
        web.setPort(9999);
        web.setEnable(true);
        web.setAllowOther(true);
        final DbWeb.H2Db tcp = web.toClone();
        tcp.setEnable(true);
        tcp.setPort(9092);
        final DbWeb dbWeb = new DbWeb(tcp, web);
        new H2(dbWeb).startH2ServerMode();
        System.in.read();
    }
}
