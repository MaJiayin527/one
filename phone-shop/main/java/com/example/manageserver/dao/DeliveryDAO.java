// src/main/java/com/example/manageserver/dao/DeliveryDAO.java
package com.example.manageserver.dao;

import com.example.manageserver.bean.Delivery;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface DeliveryDAO {

    @Insert("INSERT INTO delivery (order_no, delivery_no, username, status) " +
            "VALUES (#{orderNo}, #{deliveryNo}, #{username}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(Delivery delivery);

    @Delete("DELETE FROM delivery WHERE id = #{id}")
    int deleteById(Integer id);

    @Update("UPDATE delivery SET " +
            "order_no = #{orderNo}, " +
            "delivery_no = #{deliveryNo}, " +
            "username = #{username}, " +
            "status = #{status} " +
            "WHERE id = #{id}")
    int update(Delivery delivery);

    @Update("UPDATE delivery SET status = #{status} WHERE id = #{id}")
    int updateStatus(@Param("id") Integer id, @Param("status") Integer status);

    @Select("SELECT *, delivery_no as deliveryNo, order_no as orderNo FROM delivery WHERE id = #{id}")
    Delivery selectById(Integer id);

    @SelectProvider(type = DeliverySqlProvider.class, method = "findAllSql")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "deliveryNo", column = "delivery_no"), // 显式映射
            @Result(property = "orderNo", column = "order_no"),
            @Result(property = "status", column = "status")
    })
    List<Delivery> findAll(Map<String, Object> params);

    class DeliverySqlProvider {
        public String findAllSql(Map<String, Object> params) {
            StringBuilder sql = new StringBuilder("SELECT *, delivery_no as deliveryNo, order_no as orderNo FROM delivery");
            if (params.containsKey("keyword") && params.get("keyword") != null && !params.get("keyword").toString().isEmpty()) {
                sql.append(" WHERE delivery_no LIKE '%").append(params.get("keyword")).append("%'");
                sql.append(" OR order_no LIKE '%").append(params.get("keyword")).append("%'");
            }
            sql.append(" ORDER BY id DESC");
            System.out.println("Generated SQL: " + sql.toString()); // 打印 SQL
            return sql.toString();
        }
    }
}