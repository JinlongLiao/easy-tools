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
package io.github.jinlongliao.easy.dynamic.db.spring.aspect.demo.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (TbAdminRole)实体类
 *
 * @author makejava
 * @since 2020-09-18 13:15:35
 */
public class TbAdminRole implements Serializable {
  private static final long serialVersionUID = 750762148306840358L;

  private String id;

  private String addBy;

  private Date addTime;

  private String updateBy;

  private Date updateTime;

  private String roleName;

  private String roleValue;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getAddBy() {
    return addBy;
  }

  public void setAddBy(String addBy) {
    this.addBy = addBy;
  }

  public Date getAddTime() {
    return addTime;
  }

  public void setAddTime(Date addTime) {
    this.addTime = addTime;
  }

  public String getUpdateBy() {
    return updateBy;
  }

  public void setUpdateBy(String updateBy) {
    this.updateBy = updateBy;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public String getRoleValue() {
    return roleValue;
  }

  public void setRoleValue(String roleValue) {
    this.roleValue = roleValue;
  }

}
