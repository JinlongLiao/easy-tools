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
package  io.github.jinlongliao.easy.dynamic.db.aspect.callback;

import io.github.jinlongliao.easy.dynamic.db.core.config.ThreadConfig;
import io.github.jinlongliao.easy.dynamic.db.core.constant.KeyConstant;
import io.github.jinlongliao.easy.dynamic.db.core.interceptor.Callback;
import org.junit.Assert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author liaojinlong
 * @since 2020/9/17 14:02
 */
public class CallBackTest implements Callback {
    @Override
    public Object doFinally(Object result, Annotation annotation, Object proxy, Method method, Object[] args) {
        Assert.assertEquals(" eq ", getDbKey(), "db1");
        return result;
    }

    @Override
    public Object doError(Throwable throwable, Annotation annotation, Object result, Object proxy, Method method, Object[] args) throws Throwable {
        throwable.printStackTrace();
        return result;
    }

    @Override
    public Object after(Object result, Annotation annotation, Object proxy, Method method, Object[] args) {
        Assert.assertEquals(" eq ", getDbKey(), "db1");
        return result;
    }

    @Override
    public void before(Annotation annotation, Object proxy, Method method, Object[] args) {
        Assert.assertEquals(" eq ", getDbKey(), "db1");
    }

    private Object getDbKey() {
        final Object o = ThreadConfig.getInstance().get(KeyConstant.DB_KEY);
        System.out.println("key: " + o);
        return o;
    }
}
