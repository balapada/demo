package com.example.demo;

import com.example.demo.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.LocalDateTime;

@SpringBootTest
public class RedisStringTests {
    @Autowired
    private RedisTemplate redisTemplate;
    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testSaveUser() throws JsonProcessingException {
        User user = new User(10, "虎哥", "123", LocalDateTime.now(), LocalDateTime.now());
        //手动序列化
        String json = mapper.writeValueAsString(user);
        //写入数据
        redisTemplate.opsForValue().set("user:10", json);
        //获取数据
        String jsonUser = (String) redisTemplate.opsForValue().get("user:10");
        //手动反序列化
        User user1 = mapper.readValue(jsonUser, User.class);
        System.out.println(user1);
    }

    @Test
    void testString() {
        redisTemplate.opsForValue().set("name","虎哥");
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }
}
