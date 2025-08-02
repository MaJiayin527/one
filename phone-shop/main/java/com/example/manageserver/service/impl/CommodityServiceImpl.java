// src/main/java/com/example/manageserver/service/impl/CommodityServiceImpl.java
package com.example.manageserver.service.impl;

import com.example.manageserver.bean.Brand;
import com.example.manageserver.bean.Commodity;
import com.example.manageserver.common.PageResult;
import com.example.manageserver.common.Result;
import com.example.manageserver.dao.BrandDAO;
import com.example.manageserver.dao.CommodityDAO;
import com.example.manageserver.service.CommodityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Resource
    private CommodityDAO commodityDAO;

    @Resource
    private BrandDAO brandDAO;

    @Override
    public PageResult findAllWithBrand(Map<String, Object> params) {
        try {
            // 使用PageHelper分页
            Integer pageNum = Integer.parseInt(params.get("pageNum").toString());
            Integer pageSize = Integer.parseInt(params.get("pageSize").toString());

            PageHelper.startPage(pageNum, pageSize);
            List<Commodity> list = commodityDAO.findAllWithBrand(params);

            // 关联品牌信息
            for (Commodity commodity : list) {
                Brand brand = brandDAO.selectById(commodity.getBrandid());
                commodity.setBrand(brand);
            }

            PageInfo<Commodity> pageInfo = new PageInfo<>(list);
            return new PageResult(pageInfo.getTotal(), list);
        } catch (Exception e) {
            throw new RuntimeException("查询商品列表失败", e);
        }
    }


    @Override
    @Transactional
    public Result add(Commodity commodity) {
        int result = commodityDAO.insert(commodity);
        if (result > 0) {
            return Result.success();
        }
        return Result.error();
    }

    @Override
    @Transactional
    public Result update(Commodity commodity) {
        int result = commodityDAO.update(commodity);
        if (result > 0) {
            return Result.success();
        }
        return Result.error();
    }

    @Override
    @Transactional
    public Result deleteById(Integer id) {
        int result = commodityDAO.deleteById(id);
        if (result > 0) {
            return Result.success();
        }
        return Result.error();
    }

    @Override
    public Result findById(Integer id) {
        Commodity commodity = commodityDAO.selectById(id);
        if (commodity != null) {
            Brand brand = brandDAO.selectById(commodity.getBrandid());
            commodity.setBrand(brand);
            return Result.success(commodity);
        }
        return Result.error("商品不存在");
    }
}