package com.example.manageserver.dao;

import com.example.manageserver.bean.Peisong;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface PeisongDAO {

    @Insert("INSERT INTO peisong (orderId, addressId) VALUES(#{orderId}, #{addressId})")
    int add(Peisong peisong);

    @Update("UPDATE peisong SET orderId = #{orderId}, addressId = #{addressId} WHERE id = #{id}")
    int update(Peisong peisong);

    @Delete("DELETE FROM peisong WHERE id = #{id}")
    int delete(Integer id);

    @SelectProvider(type = PeisongSqlProvider.class, method = "getList")
    List<Peisong> getList(Map<String, Object> params);


    class PeisongSqlProvider{
        public String getList(Map<String, Object> params){
            StringBuilder sql = new StringBuilder("select * from peisong where 1=1 ");
            if(params.get("orderId") != null && !"".equals(params.get("orderId"))){
                sql.append(" and orderId = #{orderId}");
            }
            return sql.toString();
        }
    }
} 