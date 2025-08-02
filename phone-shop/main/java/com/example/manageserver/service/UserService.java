// src/main/java/com/example/manageserver/service/UserService.java
package com.example.manageserver.service;

import com.example.manageserver.bean.User;
import com.example.manageserver.common.PageResult;
import com.example.manageserver.common.Result;

import java.util.Map;

public interface UserService {

    PageResult findAll(Map<String, Object> params);

    Result findById(Integer id);

    Result add(User user);

    Result update(User user);

    Result deleteById(Integer id);

    Result login(String username, String password);

    Result register(User user);
}