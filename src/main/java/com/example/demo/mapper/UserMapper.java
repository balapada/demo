package com.example.demo.mapper;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select userId, userName, createTime, updateTime from table_user")
    List<User> list();

    @Delete("delete from table_user where userId = #{userId}")
    void deleteByUserId(Integer userId);

    @Insert("insert into table_user (userId, userName, userPassword, createTime, updateTime) VALUES " +
            "(#{userId},#{userName},#{userPassword},#{createTime},#{updateTime})")
    void insert(User user);

    void update(User user);

    @Select("select userId, userName, createTime, updateTime from table_user where userId = #{userId}")
    User queryByUserId(Integer userId);
}
