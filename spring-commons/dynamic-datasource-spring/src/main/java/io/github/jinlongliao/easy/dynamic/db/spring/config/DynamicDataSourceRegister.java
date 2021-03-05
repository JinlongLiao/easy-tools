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
package io.github.jinlongliao.easy.dynamic.db.spring.config;

import io.github.jinlongliao.easy.dynamic.db.spring.config.aspect.DataSourceAspectAdvice;
import io.github.jinlongliao.easy.dynamic.db.spring.config.aspect.DynamicAspectJExpressionPointcutAdvisor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author liaojinlong
 */
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes annotationAttributes = AnnotationAttributes
                .fromMap(importingClassMetadata.getAnnotationAttributes(DynamicDataSource.class.getName()));
        if (annotationAttributes != null) {
            registerBeanDefinitions(importingClassMetadata,
                    annotationAttributes,
                    registry,
                    generateBaseBeanName(importingClassMetadata, 0));
        }
    }

    void registerBeanDefinitions(AnnotationMetadata annotationMetadata, AnnotationAttributes annotationAttributes,
                                 BeanDefinitionRegistry registry, String beanName) {
        final String dbKey = annotationAttributes.getString("dbKey");
        final String pointCut = annotationAttributes.getString("pointCut");
        final DataSourceAspectAdvice aspectAdvice = new DataSourceAspectAdvice(dbKey);
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(DynamicAspectJExpressionPointcutAdvisor.class);

        builder.addConstructorArgValue(pointCut);
        builder.addConstructorArgValue(aspectAdvice);
        registry.registerBeanDefinition(beanName, builder.getBeanDefinition());

    }

    private static String generateBaseBeanName(AnnotationMetadata importingClassMetadata, int index) {
        return importingClassMetadata.getClassName() + "#" + DynamicDataSourceRegister.class.getSimpleName() + "#" + index;
    }

    static class RepeatingRegistrar extends DynamicDataSourceRegister {
        /**
         * {@inheritDoc}
         */
        @Override
        public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
            AnnotationAttributes annotationAttributes = AnnotationAttributes
                    .fromMap(importingClassMetadata.getAnnotationAttributes(DynamicDataSources.class.getName()));
            if (annotationAttributes != null) {
                AnnotationAttributes[] annotations = annotationAttributes.getAnnotationArray("value");
                for (int i = 0; i < annotations.length; i++) {
                    registerBeanDefinitions(importingClassMetadata, annotations[i], registry,
                            generateBaseBeanName(importingClassMetadata, i));
                }
            }
        }
    }
}
