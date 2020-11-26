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
package io.github.jinlongliao.easy.dynamic.db.spring.aspect.demo.config;


import javax.servlet.*;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


/**
 * @author liaojinlong
 * @since 2020/9/22 13:09
 */
public class MyWebApplicationInitializer implements WebApplicationInitializer {
  private static ConfigurableWebApplicationContext applicationContext = null;

  public static ConfigurableWebApplicationContext getApplicationContext() {
    return applicationContext;
  }

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    applicationContext = createWebContext(WebConfig.class, SpringConfig.class);
    ServletRegistration.Dynamic registration = servletContext
      .addServlet("dispatcher", new DispatcherServlet(applicationContext));

    registration.setLoadOnStartup(1);
    registration.addMapping("/");

  }

  /**
   * 自定义配置类来实例化一个Web Application Context
   *
   * @param annotatedClasses
   * @return
   */
  private AnnotationConfigWebApplicationContext createWebContext(Class<?>... annotatedClasses) {
    AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
    webContext.register(annotatedClasses);
    return webContext;
  }
}
