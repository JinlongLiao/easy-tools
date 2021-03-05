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
package io.github.jinlonghliao.common.aop.aspects;


import io.github.jinlonghliao.common.core.date.TimeInterval;
import io.github.jinlonghliao.common.core.lang.Console;

import java.lang.reflect.Method;

/**
 * 通过日志打印方法的执行时间的切面
 *
 * @author Looly
 */
public class TimeIntervalAspect extends SimpleAspect {
    private static final long serialVersionUID = 1L;

    private final TimeInterval interval = new TimeInterval();

    @Override
    public boolean before(Object target, Method method, Object[] args) {
        interval.start();
        return true;
    }

    @Override
    public boolean after(Object target, Method method, Object[] args, Object returnVal) {
        Console.log("Method [{}.{}] execute spend [{}]ms return value [{}]",
                target.getClass().getName(), //
                method.getName(), //
                interval.intervalMs(), //
                returnVal);
        return true;
    }
}
