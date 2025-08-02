// src/main/java/com/example/manageserver/service/CommodityService.java
package com.example.manageserver.service;

import com.example.manageserver.bean.Commodity;
import com.example.manageserver.common.PageResult;
import com.example.manageserver.common.Result;

import java.util.Map;

public interface CommodityService {

    PageResult findAllWithBrand(Map<String, Object> params);

    Result add(Commodity commodity);

    Result update(Commodity commodity);

    Result deleteById(Integer id);

    Result findById(Integer id);
}