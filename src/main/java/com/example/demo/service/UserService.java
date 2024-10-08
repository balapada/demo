package com.example.demo.service;

import com.example.demo.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;

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
     * @param user 用户对象
     */
    void update(User user);

    User queryByUserId(Integer userId) throws JsonProcessingException;
}
