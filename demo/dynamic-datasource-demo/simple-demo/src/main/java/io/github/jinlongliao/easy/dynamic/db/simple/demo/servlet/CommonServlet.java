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
package  io.github.jinlongliao.easy.dynamic.db.simple.demo.servlet;

import com.alibaba.fastjson.JSONObject;
import io.github.jinlongliao.easy.dynamic.db.core.config.ThreadConfig;
import io.github.jinlongliao.easy.dynamic.db.simple.demo.mybatis.dao.TbAdminRoleDao;
import io.github.jinlongliao.easy.dynamic.db.simple.demo.mybatis.dao.UserDao;
import io.github.jinlongliao.easy.dynamic.db.simple.demo.mybatis.datasource.EasyDbSqlSessionFactory;
import io.github.jinlongliao.easy.dynamic.db.simple.demo.mybatis.entity.TbAdminRole;
import io.github.jinlongliao.easy.dynamic.db.simple.demo.mybatis.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author liaojinlong
 * @since 2020/9/21 17:28
 */
@WebServlet(name = "common", value = {"/*"})
public class CommonServlet extends HttpServlet {
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    final String requestURI = req.getRequestURI();
    SqlSessionFactory sqlSessionFactory;
    if (requestURI.contains("admin")) {
      admin(resp);
    } else {
      if (requestURI.contains("add")) {
        addUser(req);
      }
      user(resp);
    }
  }

  private void addUser(HttpServletRequest req) {
    SqlSessionFactory sqlSessionFactory;
    ThreadConfig.getInstance().setDbKey("db2");
    SqlSession sqlSession = null;
    try {
      sqlSessionFactory = EasyDbSqlSessionFactory.getInstance().getSqlSessionFactory();
      sqlSession = sqlSessionFactory.openSession();
      final UserDao mapper = sqlSession.getMapper(UserDao.class);
      mapper.deleteByFixedCond();
      User user = new User();
      user.setId(System.currentTimeMillis());
      user.setPassword("1234");
      user.setUsername("1234");
      user.setRole("1234");
      mapper.insert(user);
      sqlSession.commit();
    } catch (Exception e) {
      sqlSession.rollback();
      e.printStackTrace();
    } finally {
      ThreadConfig.getInstance().clear(Boolean.TRUE);
      if (sqlSession != null) {
        sqlSession.close();
      }
    }
  }

  private void user(HttpServletResponse resp) throws IOException {
    SqlSessionFactory sqlSessionFactory;
    ThreadConfig.getInstance().setDbKey("db2");
    SqlSession sqlSession = null;
    try {
      sqlSessionFactory = EasyDbSqlSessionFactory.getInstance().getSqlSessionFactory();
      sqlSession = sqlSessionFactory.openSession();
      final UserDao mapper = sqlSession.getMapper(UserDao.class);
      final List<User> users = mapper.queryAll(null);
      resp.getWriter().write(JSONObject.toJSONString(users, true));
      System.out.println("JDBC: " + sqlSession.getConnection().getMetaData().getURL());
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } finally {
      ThreadConfig.getInstance().clear(Boolean.TRUE);
      if (sqlSession != null) {
        sqlSession.close();
      }
    }
  }

  private void admin(HttpServletResponse resp) throws IOException {
    SqlSessionFactory sqlSessionFactory;
    SqlSession sqlSession = null;
    /**
     * 设置 使用的数据源
     */
    ThreadConfig.getInstance().setDbKey("db1");
    try {
      sqlSessionFactory = EasyDbSqlSessionFactory.getInstance().getSqlSessionFactory();
      sqlSession = sqlSessionFactory.openSession();
      final TbAdminRoleDao mapper = sqlSession.getMapper(TbAdminRoleDao.class);
      final List<TbAdminRole> tbAdminRoles = mapper.queryAll(null);
      System.out.println("JDBC: " + sqlSession.getConnection().getMetaData().getURL());
      resp.getWriter().write(JSONObject.toJSONString(tbAdminRoles, true));
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } finally {
      /**
       * 资源释放
       */
      ThreadConfig.getInstance().clear(Boolean.TRUE);
      if (sqlSession != null) {
        sqlSession.close();
      }
    }
  }
}
