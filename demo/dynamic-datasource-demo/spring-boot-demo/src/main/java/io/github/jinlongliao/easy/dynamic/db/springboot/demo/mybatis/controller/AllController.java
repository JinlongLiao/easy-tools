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
package  io.github.jinlongliao.easy.dynamic.db.springboot.demo.mybatis.controller;

import io.github.jinlongliao.easy.dynamic.db.springboot.demo.mybatis.service.TbAdminRoleService;
import io.github.jinlongliao.easy.dynamic.db.springboot.demo.mybatis.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2020-09-18 13:11:18
 */
@RestController
@RequestMapping("/all")
public class AllController {
  /**
   * 服务对象
   */
  @Resource
  private UserService userService;
  /**
   * 服务对象
   */
  @Resource
  private TbAdminRoleService tbAdminRoleService;

  /**
   * 通过主键查询单条数据
   *
   * @return 多条数据
   */
  @GetMapping("/select")
  public List<Object> selectAll() {
    List<Object> data = new ArrayList<>();
    data.addAll(this.userService.queryAllByLimit(0, 10));
    data.addAll(this.tbAdminRoleService.queryAllByLimit(0, 10));
    return data;
  }
}
