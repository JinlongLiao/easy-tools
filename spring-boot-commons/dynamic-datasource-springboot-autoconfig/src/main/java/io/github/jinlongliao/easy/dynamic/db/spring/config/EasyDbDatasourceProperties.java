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


import io.github.jinlongliao.easy.dynamic.db.spring.config.ext.EasyDbExtDatasource;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.UUID;

/**
 * @author liaojinlong
 * @since 2020/9/17 14:51
 */
public class EasyDbDatasourceProperties implements BeanClassLoaderAware, InitializingBean {
  private Map<String, DataSourceProperties> datasource;
  private static ClassLoader classLoader;
  private EmbeddedDatabaseConnection embeddedDatabaseConnection = EmbeddedDatabaseConnection.NONE;

  public Map<String, DataSourceProperties> getDatasource() {
    return datasource;
  }

  @Override
  public void setBeanClassLoader(ClassLoader classLoader) {
    EasyDbDatasourceProperties.classLoader = classLoader;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    this.embeddedDatabaseConnection = EmbeddedDatabaseConnection.get(EasyDbDatasourceProperties.classLoader);
  }

  public void setDatasource(Map<String, DataSourceProperties> datasource) {
    this.datasource = datasource;
  }

  public static class DataSourceProperties {
    /**
     * 额外的配置 ，用于扩展
     */
    private Map<String, Object> extProps;

    /**
     * Initialize a {@link DataSourceBuilder} with the state of this instance.
     *
     * @return a {@link DataSourceBuilder} initialized with the customizations defined on
     * this instance
     */
    public DataSourceBuilder<?> initializeDataSourceBuilder() {
      return DataSourceBuilder.create(classLoader).type(getType()).driverClassName(determineDriverClassName())
        .url(determineUrl()).username(determineUsername()).password(determinePassword());
    }

    /**
     * Determine the password to use based on this configuration and the environment.
     *
     * @return the password to use
     * @since 1.4.0
     */
    public String determinePassword() {
      if (StringUtils.hasText(this.password)) {
        return this.password;
      }
      if (EmbeddedDatabaseConnection.isEmbedded(determineDriverClassName())) {
        return "";
      }
      return null;
    }

    /**
     * Determine the username to use based on this configuration and the environment.
     *
     * @return the username to use
     * @since 1.4.0
     */
    public String determineUsername() {
      if (StringUtils.hasText(this.username)) {
        return this.username;
      }
      if (EmbeddedDatabaseConnection.isEmbedded(determineDriverClassName())) {
        return "sa";
      }
      return null;
    }

    /**
     * Determine the driver to use based on this configuration and the environment.
     *
     * @return the driver to use
     * @since 1.4.0
     */
    public String determineDriverClassName() {
      if (StringUtils.hasText(this.driverClassName)) {
        Assert.state(driverClassIsLoadable(), () -> "Cannot load driver class: " + this.driverClassName);
        return this.driverClassName;
      }
      String driverClassName = null;
      if (StringUtils.hasText(this.url)) {
        driverClassName = DatabaseDriver.fromJdbcUrl(this.url).getDriverClassName();
      }
      if (!StringUtils.hasText(driverClassName)) {
        driverClassName = this.embeddedDatabaseConnection.getDriverClassName();
      }
      if (!StringUtils.hasText(driverClassName)) {
        throw new DataSourceBeanCreationException("Failed to determine a suitable driver class", this,
          this.embeddedDatabaseConnection);
      }
      return driverClassName;
    }

    private boolean driverClassIsLoadable() {
      try {
        ClassUtils.forName(this.driverClassName, null);
        return true;
      } catch (UnsupportedClassVersionError ex) {
        // Driver library has been compiled with a later JDK, propagate error
        throw ex;
      } catch (Throwable ex) {
        return false;
      }
    }

    /**
     * Determine the url to use based on this configuration and the environment.
     *
     * @return the url to use
     * @since 1.4.0
     */
    public String determineUrl() {
      if (StringUtils.hasText(this.url)) {
        return this.url;
      }
      String databaseName = determineDatabaseName();
      String url = (databaseName != null) ? this.embeddedDatabaseConnection.getUrl(databaseName) : null;
      if (!StringUtils.hasText(url)) {
        throw new DataSourceBeanCreationException("Failed to determine suitable jdbc url", this,
          this.embeddedDatabaseConnection);
      }
      return url;
    }

    /**
     * Determine the name to used based on this configuration.
     *
     * @return the database name to use or {@code null}
     * @since 2.0.0
     */
    public String determineDatabaseName() {
      if (this.generateUniqueName) {
        if (this.uniqueName == null) {
          this.uniqueName = UUID.randomUUID().toString();
        }
        return this.uniqueName;
      }
      if (StringUtils.hasLength(this.name)) {
        return this.name;
      }
      if (this.embeddedDatabaseConnection != EmbeddedDatabaseConnection.NONE) {
        return "testdb";
      }
      return null;
    }

    /**
     * Whether to generate a random datasource name.
     */
    private boolean generateUniqueName = true;

    /**
     * Fully qualified name of the connection pool implementation to use. By default, it
     * is auto-detected from the classpath.
     */
    private Class<? extends DataSource> type;
    private Class<? extends EasyDbExtDatasource> extType;
    private String uniqueName;
    private String name;

    private EmbeddedDatabaseConnection embeddedDatabaseConnection = EmbeddedDatabaseConnection.NONE;

    /**
     * Fully qualified name of the JDBC driver. Auto-detected based on the URL by default.
     */
    private String driverClassName;

    /**
     * JDBC URL of the database.
     */
    private String url;

    /**
     * Login username of the database.
     */
    private String username;

    /**
     * Login password of the database.
     */
    private String password;
    /**
     * Whether to stop if an error occurs while initializing the database.
     */
    private boolean continueOnError = false;

    /**
     * SQL scripts encoding.
     */
    private Charset sqlScriptEncoding;

    public boolean isGenerateUniqueName() {
      return generateUniqueName;
    }

    public void setGenerateUniqueName(boolean generateUniqueName) {
      this.generateUniqueName = generateUniqueName;
    }

    public Class<? extends DataSource> getType() {
      return type;
    }

    public void setType(Class<? extends DataSource> type) {
      this.type = type;
    }

    public String getUniqueName() {
      return uniqueName;
    }

    public void setUniqueName(String uniqueName) {
      this.uniqueName = uniqueName;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public EmbeddedDatabaseConnection getEmbeddedDatabaseConnection() {
      return embeddedDatabaseConnection;
    }

    public void setEmbeddedDatabaseConnection(EmbeddedDatabaseConnection embeddedDatabaseConnection) {
      this.embeddedDatabaseConnection = embeddedDatabaseConnection;
    }

    public Map<String, Object> getExtProps() {
      return extProps;
    }

    public void setExtProps(Map<String, Object> extProps) {
      this.extProps = extProps;
    }

    public boolean isContinueOnError() {
      return continueOnError;
    }

    public void setContinueOnError(boolean continueOnError) {
      this.continueOnError = continueOnError;
    }

    public Charset getSqlScriptEncoding() {
      return sqlScriptEncoding;
    }

    public void setSqlScriptEncoding(Charset sqlScriptEncoding) {
      this.sqlScriptEncoding = sqlScriptEncoding;
    }

    public String getDriverClassName() {
      return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
      this.driverClassName = driverClassName;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    public Class<? extends EasyDbExtDatasource> getExtType() {
      return extType;
    }

    public void setExtType(Class<? extends EasyDbExtDatasource> extType) {
      this.extType = extType;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }
  }

  static class DataSourceBeanCreationException extends BeanCreationException {

    private final DataSourceProperties properties;

    private final EmbeddedDatabaseConnection connection;

    DataSourceBeanCreationException(String message, DataSourceProperties properties,
                                    EmbeddedDatabaseConnection connection) {
      super(message);
      this.properties = properties;
      this.connection = connection;
    }

    DataSourceProperties getProperties() {
      return this.properties;
    }

    EmbeddedDatabaseConnection getConnection() {
      return this.connection;
    }

  }
}

