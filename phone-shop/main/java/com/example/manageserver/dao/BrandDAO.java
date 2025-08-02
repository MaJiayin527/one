// src/main/java/com/example/manageserver/dao/BrandDAO.java
package com.example.manageserver.dao;

import com.example.manageserver.bean.Brand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BrandDAO {

    @Select("SELECT * FROM brand WHERE id = #{id}")
    Brand selectById(Integer id);
}