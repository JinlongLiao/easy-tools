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
package  io.github.jinlongliao.easy.dynamic.db.springboot.demo.mybatis.service.impl;

import io.github.jinlongliao.easy.dynamic.db.springboot.demo.mybatis.dao.UserDao;
import io.github.jinlongliao.easy.dynamic.db.springboot.demo.mybatis.entity.User;
import io.github.jinlongliao.easy.dynamic.db.springboot.demo.mybatis.service.UserService;
import io.github.jinlongliao.easy.dynamic.db.core.annotation.DbDataSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2020-09-18 13:11:17
 */
@Service
public class UserServiceImpl implements UserService {
  @Resource
  private UserDao userDao;

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  @Override
  @DbDataSource("db2")
  public User queryById(Long id) {
    return this.userDao.queryById(id);
  }

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit  查询条数
   * @return 对象列表
   */
  @Override
  @DbDataSource("db2")
  public List<User> queryAllByLimit(int offset, int limit) {
    return this.userDao.queryAllByLimit(offset, limit);
  }

  /**
   * 新增数据
   *
   * @param user 实例对象
   * @return 实例对象
   */
  @Override
  @DbDataSource("db2")
  public User insert(User user) {
    this.userDao.insert(user);
    return user;
  }

  /**
   * 修改数据
   *
   * @param user 实例对象
   * @return 实例对象
   */
  @Override
  @DbDataSource("db2")
  public User update(User user) {
    this.userDao.update(user);
    return this.queryById(user.getId());
  }

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  @Override
  @DbDataSource("db2")
  public boolean deleteById(Long id) {
    return this.userDao.deleteById(id) > 0;
  }
}
