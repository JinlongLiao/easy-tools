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
package io.github.jinlongliao.easy.dynamic.db.aspect.mybatis.dao;

import io.github.jinlongliao.easy.dynamic.db.aspect.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-18 13:11:15
 */
@Mapper
public interface UserDao {

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  User queryById(Long id);

  /**
   * 查询指定行数据
   *
   * @param offset 查询起始位置
   * @param limit  查询条数
   * @return 对象列表
   */
  List<User> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


  /**
   * 通过实体作为筛选条件查询
   *
   * @param user 实例对象
   * @return 对象列表
   */
  List<User> queryAll(User user);

  /**
   * 新增数据
   *
   * @param user 实例对象
   * @return 影响行数
   */
  int insert(User user);

  /**
   * 修改数据
   *
   * @param user 实例对象
   * @return 影响行数
   */
  int update(User user);

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 影响行数
   */
  int deleteById(Long id);

  /**
   * 固定XML 中的 条件
   *
   * @return 影响行数
   */
  int deleteByFixedCond();
}
