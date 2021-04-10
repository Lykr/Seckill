package com.learning.seckill.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.learning.seckill.pojo.User;

@Mapper
public interface UserDao {

    @Select("SELECT * FROM user WHERE phone = #{phone}")
    User getByPhone(Long phone);

    @Insert("INSERT INTO user(id, name) VALUES (#{id}, #{name})")
    boolean insert(User user);
}
