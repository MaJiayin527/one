// src/main/java/com/example/manageserver/service/impl/DeliveryServiceImpl.java
package com.example.manageserver.service.impl;

import com.example.manageserver.bean.Delivery;
import com.example.manageserver.common.PageResult;
import com.example.manageserver.common.Result;
import com.example.manageserver.dao.DeliveryDAO;
import com.example.manageserver.service.DeliveryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Resource
    private DeliveryDAO deliveryDAO;

    @Override
    public PageResult findAll(Map<String, Object> params) {
        // 从 params 中提取分页参数，如果没有则使用默认值
        int pageNum = params.containsKey("pageNum") ? Integer.parseInt(params.get("pageNum").toString()) : 1;
        int pageSize = params.containsKey("pageSize") ? Integer.parseInt(params.get("pageSize").toString()) : 10;

        // 使用 PageHelper 分页
        PageHelper.startPage(pageNum, pageSize);
        List<Delivery> list = deliveryDAO.findAll(params);

        // 使用 PageInfo 获取分页信息
        PageInfo<Delivery> pageInfo = new PageInfo<>(list);
        return new PageResult(pageInfo.getTotal(), list);
    }

    @Override
    @Transactional
    public Result add(Delivery delivery) {
        int result = deliveryDAO.insert(delivery);
        if (result > 0) {
            return Result.success();
        }
        return Result.error("添加配送信息失败");
    }

    @Override
    @Transactional
    public Result update(Delivery delivery) {
        int result = deliveryDAO.update(delivery);
        if (result > 0) {
            return Result.success();
        }
        return Result.error("更新配送信息失败");
    }

    @Override
    @Transactional
    public Result deleteById(Integer id) {
        int result = deliveryDAO.deleteById(id);
        if (result > 0) {
            return Result.success();
        }
        return Result.error("删除配送信息失败");
    }

    @Override
    public Result findById(Integer id) {
        Delivery delivery = deliveryDAO.selectById(id);
        if (delivery != null) {
            return Result.success(delivery);
        }
        return Result.error("配送信息不存在");
    }



    @Override
    @Transactional
    public Result updateStatus(Integer deliveryId, Integer status) {
        int result = deliveryDAO.updateStatus(deliveryId, status);
        if (result > 0) {
            return Result.success();
        }
        return Result.error("更新配送状态失败");
    }
}