package com.learning.seckill.dao;

import com.learning.seckill.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface GoodsDao {

    @Select("SELECT * FROM goods")
    List<Goods> getGoodsList();

    @Select("SELECT * FROM goods where id = #{id}")
    Goods getGoods(@Param("id") Long id);

    @Update("UPDATE goods SET stock = stock - 1 where id = #{id}")
    Boolean reduceStock(@Param("id") Long id);

}
