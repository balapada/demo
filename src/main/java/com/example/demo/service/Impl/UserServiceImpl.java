package com.example.demo.service.Impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    private static final ObjectMapper mapper = new ObjectMapper();


    @Override
    public List<User> list() {
        return userMapper.list();
    }

    @Override
    public void delete(Integer userId) {
        userMapper.deleteByUserId(userId);
    }

    @Override
    public void add(User user) {
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public User queryByUserId(Integer userId) throws JsonProcessingException {
        String key = "cache:user:" + userId;
        //从redis查询用户缓存
        String userJson = (String) redisTemplate.opsForValue().get(key);
        //判断是否存在
        if(StringUtils.isNotBlank(userJson)) {
            //存在则直接返回
            return mapper.readValue(userJson, User.class);
        }
        //不存在，根据id查询数据库
        User user = userMapper.queryByUserId(userId);
        //写入redis
        redisTemplate.opsForValue().set(key, mapper.writeValueAsString(user));
        //返回
        return user;
    }
}
