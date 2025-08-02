// src/main/java/com/example/manageserver/dao/CommodityDAO.java
package com.example.manageserver.dao;

import com.example.manageserver.bean.Commodity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommodityDAO {

    /**
     * 添加商品
     */
    @Insert("INSERT INTO commodity (name, price, url, productiondate, brandid, sales, updatetime) " +
            "VALUES (#{name}, #{price}, #{url}, #{productiondate}, #{brandid}, #{sales}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(Commodity commodity);


    /**
     * 根据ID删除商品
     */
    @Delete("DELETE FROM commodity WHERE id = #{id}")
    int deleteById(Integer id);

    /**
     * 更新商品
     */
    @Update("UPDATE commodity SET " +
            "name = #{name}, " +
            "price = #{price}, " +
            "url = #{url}, " +
            "productiondate = #{productiondate}, " +
            "brandid = #{brandid}, " +
            "sales = #{sales}, " +
            "updatetime = NOW() " +
            "WHERE id = #{id}")
    int update(Commodity commodity);

    /**
     * 根据ID查询商品
     */
    @Select("SELECT * FROM commodity WHERE id = #{id}")
    Commodity selectById(Integer id);

    /**
     * 查询所有商品（带品牌信息）
     */
    @SelectProvider(type = CommoditySqlProvider.class, method = "findAllWithBrandSql")
    List<Commodity> findAllWithBrand(Map<String, Object> params);

    @Select("SELECT COUNT(*) FROM commodity c WHERE 1=1 " +
            "<if test='keyword != null'>AND c.name LIKE CONCAT('%', #{keyword}, '%')</if>")
    long countWithBrand(Map<String, Object> params);

    class CommoditySqlProvider {
        public String findAllWithBrandSql(Map<String, Object> params) {
            StringBuilder sql = new StringBuilder("SELECT c.* FROM commodity c WHERE 1=1 ");

            // 替换为 Java 原生判空逻辑
            if (params.containsKey("keyword") && params.get("keyword") != null && !params.get("keyword").toString().trim().isEmpty()) {
                sql.append("AND c.name LIKE CONCAT('%', #{keyword}, '%') ");
            }

            sql.append("ORDER BY c.id DESC");
            return sql.toString();
        }
    }
}