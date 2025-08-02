package com.example.manageserver.service.impl;

import com.example.manageserver.bean.Peisong;
import com.example.manageserver.common.Result;
import com.example.manageserver.dao.PeisongDAO;
import com.example.manageserver.service.PeisongService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class PeisongServiceImpl implements PeisongService {

    @Resource
    private PeisongDAO peisongDAO;

    @Override
    public Result add(Peisong peisong) {
        if(peisongDAO.add(peisong)>0){
            return Result.success();
        }
        return Result.error();
    }

    @Override
    public Result update(Peisong peisong) {
        if(peisongDAO.update(peisong)>0){
            return Result.success();
        }
        return Result.error();
    }

    @Override
    public Result delete(Integer id) {
        if(peisongDAO.delete(id)>0){
            return Result.success();
        }
        return Result.error();
    }

    @Override
    public Result getList(Map<String, Object> params) {
        List<Peisong> list = peisongDAO.getList(params);
        return Result.success(list);
    }
} 