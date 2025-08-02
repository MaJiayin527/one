// src/main/java/com/example/manageserver/dao/OrderDAO.java
package com.example.manageserver.dao;

import com.example.manageserver.bean.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderDAO {

    /**
     * 添加订单
     */
    @Insert("INSERT INTO orders (orderNo, commodityId, commodityName, brandId, userId, username, phone, address, quantity, totalPrice, status, orderTime) " +
            "VALUES (#{orderNo}, #{commodityId}, #{commodityName}, #{brandId}, #{userId}, #{username}, #{phone}, #{address}, #{quantity}, #{totalPrice}, #{status}, #{orderTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(Order order);

    /**
     * 根据ID删除订单
     */
    @Delete("DELETE FROM orders WHERE id = #{id}")
    int deleteById(Integer id);

    /**
     * 更新订单
     */
    @Update("UPDATE orders SET " +
            "orderNo = #{orderNo}, " +
            "commodityId = #{commodityId}, " +
            "commodityName = #{commodityName}, " +
            "brandId = #{brandId}, " +
            "userId = #{userId}, " +
            "username = #{username}, " +
            "phone = #{phone}, " +
            "address = #{address}, " +
            "quantity = #{quantity}, " +
            "totalPrice = #{totalPrice}, " +
            "status = #{status}, " +
            "orderTime = #{orderTime} " +
            "WHERE id = #{id}")
    int update(Order order);

    /**
     * 更新订单状态
     */
    @Update("UPDATE orders SET status = #{status} WHERE id = #{id}")
    int updateStatus(@Param("id") Integer id, @Param("status") Integer status);

    /**
     * 根据ID查询订单
     */
    @Select("SELECT * FROM orders WHERE id = #{id}")
    Order selectById(Integer id);

    @Select("SELECT * FROM orders WHERE orderNo = #{orderNo}")
    Order findByOrderNo(String orderNo);

    /**
     * 查询所有订单
     */
    @SelectProvider(type = OrderSqlProvider.class, method = "findAllSql")
    List<Order> findAll(Map<String, Object> params);

    class OrderSqlProvider {
        public String findAllSql(Map<String, Object> params) {
            StringBuilder sql = new StringBuilder("SELECT * FROM orders");
            if (params.containsKey("status") && params.get("status") != null) {
                sql.append(" WHERE status = #{status}"); // 按 status 筛选
            }
            sql.append(" ORDER BY orderTime DESC"); // 按时间倒序
            return sql.toString();
        }
    }
}