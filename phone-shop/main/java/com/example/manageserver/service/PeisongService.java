package com.example.manageserver.service;

import com.example.manageserver.bean.Peisong;
import com.example.manageserver.common.Result;

import java.util.Map;

public interface PeisongService {

    Result add(Peisong peisong);

    Result update(Peisong peisong);

    Result delete(Integer id);

    Result getList(Map<String, Object> params);
} 