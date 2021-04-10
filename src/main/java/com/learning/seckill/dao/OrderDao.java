package com.learning.seckill.dao;

import com.learning.seckill.pojo.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderDao {

    @Insert("INSERT INTO `order`(user_phone, goods_id, delivery_addr_id, status, price, create_date, pay_date)" +
            "VALUES (#{userPhone}, #{goodsId}, #{deliveryAddrId}, #{status}, #{price}, #{createDate}, #{payDate})")
    void insert(Order order);

    @Select("SELECT * FROM `order` where user_phone = #{userPhone} and goods_id = #{goodsId}")
    Order getByUserIdGoodsId(@Param("userPhone") Long userPhone, @Param("goodsId") Long goodsId);

}
