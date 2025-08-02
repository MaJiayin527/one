// src/main/java/com/example/manageserver/dao/UserDAO.java
package com.example.manageserver.dao;

import com.example.manageserver.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDAO {

    /**
     * 添加用户
     */
    @Insert("INSERT INTO user (username, password, phone, email, status, registerTime) " +
            "VALUES (#{username}, #{password}, #{phone}, #{email}, #{status}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(User user);

    /**
     * 根据ID删除用户
     */
    @Delete("DELETE FROM user WHERE id = #{id}")
    int deleteById(Integer id);

    /**
     * 更新用户
     */
    @Update("UPDATE user SET " +
            "username = #{username}, " +
            "password = #{password}, " +
            "phone = #{phone}, " +
            "email = #{email}, " +
            "status = #{status} " +
            "WHERE id = #{id}")
    int update(User user);

    /**
     * 根据ID查询用户
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    User selectById(Integer id);

    /**
     * 根据用户名查询用户
     */
    @Select("SELECT * FROM user WHERE username = #{username}")
    User selectByUsername(String username);

    /**
     * 查询所有用户
     */
    @SelectProvider(type = UserSqlProvider.class, method = "findAllSql")
    List<User> findAll(Map<String, Object> params);

    @Select("SELECT * FROM user")
    List<User> findAll();

    class UserSqlProvider {
        public String findAllSql(Map<String, Object> params) {
            StringBuilder sql = new StringBuilder("SELECT * FROM user");

            // 处理搜索关键字
            if (params.containsKey("keyword") && params.get("keyword") != null) {
                String keyword = (String) params.get("keyword");
                sql.append(" WHERE username LIKE '%").append(keyword).append("%'")
                        .append(" OR phone LIKE '%").append(keyword).append("%'");
            }

            sql.append(" ORDER BY id DESC");
            return sql.toString();
        }
    }
}