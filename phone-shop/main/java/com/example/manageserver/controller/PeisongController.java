package com.example.manageserver.controller;

import com.example.manageserver.bean.Peisong;
import com.example.manageserver.common.Result;
import com.example.manageserver.service.PeisongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/peisong")
public class PeisongController {

    @Autowired
    private PeisongService peisongService;

    @PostMapping("/add")
    public Result add(@RequestBody Peisong peisong){
        return peisongService.add(peisong);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Peisong peisong){
        return peisongService.update(peisong);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        return peisongService.delete(id);
    }

    @PostMapping("/list")
    public Result getList(@RequestBody Map<String, Object> params){
        return peisongService.getList(params);
    }

} 