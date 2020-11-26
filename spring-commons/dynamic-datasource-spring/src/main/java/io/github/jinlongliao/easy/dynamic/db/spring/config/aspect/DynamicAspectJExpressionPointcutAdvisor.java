/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.jinlongliao.easy.dynamic.db.spring.config.aspect;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.aop.support.AbstractGenericPointcutAdvisor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.lang.Nullable;

/**
 * 修改 Spring
 *
 * @author liaojinlong
 * @since 2020/11/26 23:48
 */

public class DynamicAspectJExpressionPointcutAdvisor extends AspectJExpressionPointcutAdvisor {
    public DynamicAspectJExpressionPointcutAdvisor(String express, Advice advice) {
        super();
        this.setExpression(express);
        this.setAdvice(advice);
    }
}
