// src/main/java/com/example/manageserver/controller/OrderController.java
package com.example.manageserver.controller;

import com.example.manageserver.bean.Order;
import com.example.manageserver.common.PageResult;
import com.example.manageserver.common.Result;
import com.example.manageserver.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/findall")
    public Result findAll(@RequestParam Map<String, Object> params) {
        try {
            // 调用Service层获取分页数据
            PageResult pageResult = orderService.findAll(params);

            // 返回标准化结构
            Map<String, Object> data = new HashMap<>();
            data.put("list", pageResult.getList()); // 订单列表
            data.put("total", pageResult.getTotal()); // 总条数

            return Result.success(data); // 包裹在data字段中
        } catch (Exception e) {
            return Result.error("查询订单失败: " + e.getMessage());
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody Order order) {
        // 确保orderTime不为空
        if (order.getOrderTime() == null) {
            order.setOrderTime(new Date());
        }
        return orderService.update(order);
    }

    @GetMapping("/deletebyid")
    public Result deleteById(@RequestParam Integer id) {
        return orderService.deleteById(id);
    }

    @GetMapping("/findbyid/{id}")
    public Result findById(@PathVariable Integer id) {
        return orderService.findById(id);
    }

    @PostMapping("/updatestatus")
    public Result updateStatus(@RequestBody Map<String, Object> params) {
        Integer orderId = (Integer) params.get("id");
        Integer status = (Integer) params.get("status");
        return orderService.updateStatus(orderId, status);
    }
}