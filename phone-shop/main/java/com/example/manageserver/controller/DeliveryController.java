// src/main/java/com/example/manageserver/controller/DeliveryController.java
package com.example.manageserver.controller;

import com.example.manageserver.bean.Delivery;
import com.example.manageserver.common.PageResult;
import com.example.manageserver.common.Result;
import com.example.manageserver.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @GetMapping("/findall")
    public PageResult findAll(@RequestParam Map<String, Object> params) {
        return deliveryService.findAll(params);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Delivery delivery) {
        return deliveryService.add(delivery);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Delivery delivery) {
        return deliveryService.update(delivery);
    }

    @GetMapping("/deletebyid")
    public Result deleteById(@RequestParam Integer id) {
        return deliveryService.deleteById(id);
    }

    @GetMapping("/findbyid/{id}")
    public Result findById(@PathVariable Integer id) {
        return deliveryService.findById(id);
    }

    @PostMapping("/updatestatus")
    public Result updateStatus(@RequestBody Map<String, Object> params) {
        Integer deliveryId = (Integer) params.get("id");
        Integer status = (Integer) params.get("status");
        return deliveryService.updateStatus(deliveryId, status);
    }
}