// src/main/java/com/example/manageserver/service/OrderService.java
package com.example.manageserver.service;

import com.example.manageserver.bean.Order;
import com.example.manageserver.common.PageResult;
import com.example.manageserver.common.Result;

import java.util.Map;

public interface OrderService {

    PageResult findAll(Map<String, Object> params);

    Result add(Order order);

    Result update(Order order);

    Result deleteById(Integer id);

    Result findById(Integer id);

    Result updateStatus(Integer orderId, Integer status);
}