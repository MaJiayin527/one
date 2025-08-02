// src/main/java/com/example/manageserver/controller/UserController.java
package com.example.manageserver.controller;

import com.example.manageserver.bean.User;
import com.example.manageserver.common.PageResult;
import com.example.manageserver.common.Result;
import com.example.manageserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/findall")
    public PageResult findAll(@RequestParam Map<String, Object> params) {
        return userService.findAll(params);
    }

    @GetMapping("/findbyid/{id}")
    public Result findById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        return userService.add(user);
    }

    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        return userService.update(user);
    }

    @GetMapping("/deletebyid/{id}")
    public Result deleteById(@PathVariable Integer id) {
        return userService.deleteById(id);
    }

    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        String password = params.get("password");
        return userService.login(username, password);
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        return userService.register(user);
    }
}