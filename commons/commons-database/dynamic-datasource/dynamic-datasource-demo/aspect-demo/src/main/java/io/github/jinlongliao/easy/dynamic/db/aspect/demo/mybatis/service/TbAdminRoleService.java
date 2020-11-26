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
package  io.github.jinlongliao.easy.dynamic.db.aspect.demo.mybatis.service;


import io.github.jinlongliao.easy.dynamic.db.aspect.demo.mybatis.entity.TbAdminRole;

import java.util.List;

/**
 * (TbAdminRole)表服务接口
 *
 * @author makejava
 * @since 2020-09-18 13:15:35
 */
public interface TbAdminRoleService {

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  TbAdminRole queryById(String id);

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit  查询条数
   * @return 对象列表
   */
  List<TbAdminRole> queryAllByLimit(int offset, int limit);

  /**
   * 新增数据
   *
   * @param tbAdminRole 实例对象
   * @return 实例对象
   */
  TbAdminRole insert(TbAdminRole tbAdminRole);

  /**
   * 修改数据
   *
   * @param tbAdminRole 实例对象
   * @return 实例对象
   */
  TbAdminRole update(TbAdminRole tbAdminRole);

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  boolean deleteById(String id);

}
