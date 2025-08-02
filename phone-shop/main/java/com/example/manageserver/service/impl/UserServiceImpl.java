// src/main/java/com/example/manageserver/service/impl/UserServiceImpl.java
package com.example.manageserver.service.impl;

import com.example.manageserver.bean.User;
import com.example.manageserver.common.PageResult;
import com.example.manageserver.common.Result;
import com.example.manageserver.dao.UserDAO;
import com.example.manageserver.service.UserService;
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
public class UserServiceImpl implements UserService {

    @Resource
    private UserDAO userDAO;

    @Override
    public PageResult findAll(Map<String, Object> params) {
        int pageNum = params.containsKey("pageNum") ? Integer.parseInt(params.get("pageNum").toString()) : 1;
        int pageSize = params.containsKey("pageSize") ? Integer.parseInt(params.get("pageSize").toString()) : 10;

        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userDAO.findAll(params);

        PageInfo<User> pageInfo = new PageInfo<>(list);
        return new PageResult(pageInfo.getTotal(), list);
    }

    @Override
    public Result findById(Integer id) {
        User user = userDAO.selectById(id);
        if (user != null) {
            return Result.success(user);
        }
        return Result.error("用户不存在");
    }

    @Override
    @Transactional
    public Result add(User user) {
        // 检查用户名是否已存在
        User existingUser = userDAO.selectByUsername(user.getUsername());
        if (existingUser != null) {
            return Result.error("用户名已存在");
        }

        int result = userDAO.insert(user);
        if (result > 0) {
            return Result.success();
        }
        return Result.error();
    }

    @Override
    @Transactional
    public Result update(User user) {
        int result = userDAO.update(user);
        if (result > 0) {
            return Result.success();
        }
        return Result.error();
    }

    @Override
    @Transactional
    public Result deleteById(Integer id) {
        int result = userDAO.deleteById(id);
        if (result > 0) {
            return Result.success();
        }
        return Result.error();
    }

    @Override
    public Result login(String username, String password) {
        User user = userDAO.selectByUsername(username);
        if (user == null) {
            return Result.error("用户名不存在");
        }

        if (!user.getPassword().equals(password)) {
            return Result.error("密码错误");
        }

        // 登录成功，返回用户信息（不包含密码）
        Map<String, Object> resultData = new HashMap<>();
        resultData.put("id", user.getId());
        resultData.put("username", user.getUsername());
        resultData.put("phone", user.getPhone());
        resultData.put("email", user.getEmail());
        resultData.put("status", user.getStatus());
        resultData.put("registerTime", user.getRegisterTime());

        return Result.success(resultData);
    }

    @Override
    @Transactional
    public Result register(User user) {
        // 检查用户名是否已存在
        User existingUser = userDAO.selectByUsername(user.getUsername());
        if (existingUser != null) {
            return Result.error("用户名已存在");
        }

        int result = userDAO.insert(user);
        if (result > 0) {
            return Result.success();
        }
        return Result.error();
    }
}