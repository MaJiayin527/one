// src/main/java/com/example/manageserver/service/impl/OrderServiceImpl.java
package com.example.manageserver.service.impl;

import com.example.manageserver.bean.Order;
import com.example.manageserver.common.PageResult;
import com.example.manageserver.common.Result;
import com.example.manageserver.dao.OrderDAO;
import com.example.manageserver.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDAO orderDAO;

    @Override
    public PageResult findAll(Map<String, Object> params) {
        int pageNum = params.containsKey("pageNum") ? Integer.parseInt(params.get("pageNum").toString()) : 1;
        int pageSize = params.containsKey("pageSize") ? Integer.parseInt(params.get("pageSize").toString()) : 10;

        PageHelper.startPage(pageNum, pageSize);
        List<Order> list = orderDAO.findAll(params);

        PageInfo<Order> pageInfo = new PageInfo<>(list);
        return new PageResult(pageInfo.getTotal(), list);
    }

    @Override
    @Transactional
    public Result add(Order order) {
        int result = orderDAO.insert(order);
        if (result > 0) {
            return Result.success();
        }
        return Result.error();
    }

    @Override
    @Transactional
    public Result update(Order order) {
        int result = orderDAO.update(order);
        if (result > 0) {
            return Result.success();
        }
        return Result.error();
    }

    @Override
    @Transactional
    public Result deleteById(Integer id) {
        int result = orderDAO.deleteById(id);
        if (result > 0) {
            return Result.success();
        }
        return Result.error();
    }

    @Override
    public Result findById(Integer id) {
        Order order = orderDAO.selectById(id);
        if (order != null) {
            return Result.success(order);
        }
        return Result.error("订单不存在");
    }

    @Override
    @Transactional
    public Result updateStatus(Integer orderId, Integer status) {
        int result = orderDAO.updateStatus(orderId, status);
        if (result > 0) {
            return Result.success();
        }
        return Result.error("更新订单状态失败");
    }
}