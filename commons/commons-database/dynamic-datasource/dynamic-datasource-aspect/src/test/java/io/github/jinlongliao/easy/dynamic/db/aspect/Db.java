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
package io.github.jinlongliao.easy.dynamic.db.aspect;

import io.github.jinlongliao.easy.dynamic.db.aspect.callback.CallBackTest;
import io.github.jinlongliao.easy.dynamic.db.core.annotation.DbDataSource;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class Db {
    @DbDataSource(value = "db1", dbCall = CallBackTest.class)
    public void getDb() {
        System.out.println("Date: " + DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now()));
    }
}
