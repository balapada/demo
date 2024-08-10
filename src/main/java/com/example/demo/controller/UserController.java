package com.example.demo.controller;

import com.example.demo.pojo.Result;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public Result list(){
        log.info("查询所有用户");
        List<User> userList = userService.list();
        return Result.success(userList);
    }

    @GetMapping("/{userId}")
    public Result queryUserByUserId(@PathVariable Integer userId){
        log.info("根据userId查询用户");
        User user = userService.queryByUserId(userId);
        return Result.success(user);
    }

    @DeleteMapping("/{userId}")
    public Result delete(@PathVariable Integer userId){
        log.info("根据userId删除用户");
        userService.delete(userId);
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody User user){
        log.info("新增用户:{}",user);
        userService.add(user);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody User user){
        log.info("修改用户:{}",user);
        userService.update(user);
        return Result.success();
    }
}
