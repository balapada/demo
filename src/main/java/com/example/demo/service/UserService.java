package com.example.demo.service;

import com.example.demo.pojo.User;

import java.util.List;

public interface UserService {
    List<User> list();

    /**
     * 根据userId删除用户
     * @param userId 用户id
     */
    void delete(Integer userId);

    /**
     * 新增用户
     * @param user 用户对象
     */
    void add(User user);

    /**
     * 修改用户
     * @param user
     */
    void update(User user);
}
