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
package io.github.jinlongliao.easy.dynamic.db.aspect.mybatis.service.impl;

import io.github.jinlongliao.easy.dynamic.db.aspect.mybatis.dao.TbAdminRoleDao;
import io.github.jinlongliao.easy.dynamic.db.aspect.mybatis.entity.TbAdminRole;
import io.github.jinlongliao.easy.dynamic.db.aspect.mybatis.service.TbAdminRoleService;
import io.github.jinlongliao.easy.dynamic.db.core.annotation.DbDataSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TbAdminRole)表服务实现类
 *
 * @author makejava
 * @since 2020-09-18 13:15:35
 */
@Service
public class TbAdminRoleServiceImpl implements TbAdminRoleService {
  @Resource
  private TbAdminRoleDao tbAdminRoleDao;

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  @Override
  @DbDataSource("db1")
  public TbAdminRole queryById(String id) {
    return this.tbAdminRoleDao.queryById(id);
  }

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit  查询条数
   * @return 对象列表
   */
  @Override
  @DbDataSource("db1")
  public List<TbAdminRole> queryAllByLimit(int offset, int limit) {
    return this.tbAdminRoleDao.queryAllByLimit(offset, limit);
  }

  /**
   * 新增数据
   *
   * @param tbAdminRole 实例对象
   * @return 实例对象
   */
  @Override
  @DbDataSource("db1")
  public TbAdminRole insert(TbAdminRole tbAdminRole) {
    this.tbAdminRoleDao.insert(tbAdminRole);
    return tbAdminRole;
  }

  /**
   * 修改数据
   *
   * @param tbAdminRole 实例对象
   * @return 实例对象
   */
  @Override
  @DbDataSource("db1")
  public TbAdminRole update(TbAdminRole tbAdminRole) {
    this.tbAdminRoleDao.update(tbAdminRole);
    return this.queryById(tbAdminRole.getId());
  }

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  @Override
  @DbDataSource("db1")
  public boolean deleteById(String id) {
    return this.tbAdminRoleDao.deleteById(id) > 0;
  }
}
