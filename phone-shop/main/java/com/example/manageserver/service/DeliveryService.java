// src/main/java/com/example/manageserver/service/DeliveryService.java
package com.example.manageserver.service;

import com.example.manageserver.bean.Delivery;
import com.example.manageserver.common.PageResult;
import com.example.manageserver.common.Result;

import java.util.Map;

public interface DeliveryService {

    PageResult findAll(Map<String, Object> params);

    Result add(Delivery delivery);

    Result update(Delivery delivery);

    Result deleteById(Integer id);

    Result findById(Integer id);

    Result updateStatus(Integer deliveryId, Integer status);
}