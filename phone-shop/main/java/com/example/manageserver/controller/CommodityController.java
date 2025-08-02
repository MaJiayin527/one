package com.example.manageserver.controller;

import com.example.manageserver.bean.Commodity;
import com.example.manageserver.common.PageResult;
import com.example.manageserver.common.Result;
import com.example.manageserver.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Date;

@RestController
@RequestMapping("/commodity")
public class CommodityController {

    @Resource
    private CommodityService commodityService;

    // 图片存储路径配置
    @Value("${commodity.image-dir:src/main/resources/static/images/}")
    private String IMAGE_DIR;

    // 图片访问基础URL
    @Value("${commodity.image-base-url:http://localhost:8080/static/images/}")
    private String IMAGE_BASE_URL;

    /**
     * 分页查询商品（带品牌信息） - 修改为POST请求
     */
    @PostMapping("/list")
    public Result list(@RequestBody Map<String, Object> params) {
        try {
            // 确保参数中有必要的分页字段
            if (!params.containsKey("pageNum")) {
                params.put("pageNum", 1);
            }
            if (!params.containsKey("pageSize")) {
                params.put("pageSize", 10);
            }

            PageResult pageResult = commodityService.findAllWithBrand(params);
            return Result.success(pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("查询商品列表失败: " + e.getMessage());
        }
    }

    /**
     * 根据ID查询商品
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Result result = commodityService.findById(id);

        // 处理单个商品的图片URL
        if (result.getData() instanceof Commodity) {
            processCommodity((Commodity) result.getData());
        }

        return result;
    }

    /**
     * 添加商品（支持图片上传）
     */
    @PostMapping("/add")
    public Result add(@RequestBody Commodity commodity) {
        try {

            // 设置默认图片URL（如果需要）
            if (commodity.getUrl() == null || commodity.getUrl().isEmpty()) {
                commodity.setUrl("default-product-image.jpg");
            }

            // 设置当前时间为修改时间
            if (commodity.getProductiondate() == null) {
                commodity.setProductiondate(new Date());
            }

            return commodityService.add(commodity);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("添加商品失败: " + e.getMessage());
        }
    }

    // 在CommodityController.java中添加以下方法
    /**
     * 更新商品信息
     */
    @PostMapping("/update")
    public Result update(@RequestBody Commodity commodity) {
        try {


            // 设置更新时间
            commodity.setUpdatetime(new Date());

            // 处理图片URL
            if (commodity.getUrl() == null || commodity.getUrl().isEmpty()) {
                commodity.setUrl("default-product-image.jpg");
            }

            return commodityService.update(commodity);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新商品失败: " + e.getMessage());
        }
    }



    /**
     * 添加删除
     */
    @DeleteMapping("/deleteById")
    public Result deleteById(@RequestParam Integer id) {
        return commodityService.deleteById(id);
    }

    /**
     * 处理单个商品
     */
    private void processCommodity(Commodity commodity) {
        if (commodity != null && commodity.getUrl() != null) {
            // 直接拼接，不再移除images/
            commodity.setUrl(IMAGE_BASE_URL + commodity.getUrl());
        }
    }

    /**
     * 处理商品列表
     */
    private void processCommodityList(List<Commodity> commodityList) {
        if (commodityList != null) {
            for (Commodity commodity : commodityList) {
                processCommodity(commodity);
            }
        }
    }
}