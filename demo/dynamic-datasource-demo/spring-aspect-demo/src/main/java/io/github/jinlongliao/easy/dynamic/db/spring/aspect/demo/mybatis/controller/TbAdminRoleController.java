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
package io.github.jinlongliao.easy.dynamic.db.spring.aspect.demo.mybatis.controller;

import io.github.jinlongliao.easy.dynamic.db.spring.aspect.demo.mybatis.entity.TbAdminRole;
import io.github.jinlongliao.easy.dynamic.db.spring.aspect.demo.mybatis.service.TbAdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TbAdminRole)表控制层
 *
 * @author makejava
 * @since 2020-09-18 13:15:35
 */
@RestController
@RequestMapping("/admin")
public class TbAdminRoleController {
  /**
   * 服务对象
   */
  @Autowired
  private TbAdminRoleService tbAdminRoleService;

  /**
   * 10数据
   *
   * @return 多数据
   */
  @GetMapping("/select")
  public List<TbAdminRole> select() {
    return this.tbAdminRoleService.queryAllByLimit(0, 10);
  }

}
