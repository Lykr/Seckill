package com.learning.seckill.service;

import com.learning.seckill.dao.OrderDao;
import com.learning.seckill.pojo.Goods;
import com.learning.seckill.pojo.Order;
import com.learning.seckill.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {
    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderDao orderDao;

    public Order getByUserIdGoodsId(Long userPhone, Long goodsId) {
        return orderDao.getByUserIdGoodsId(userPhone, goodsId);
    }

    public Order secKill(User user, Goods goods) {
        Order order = new Order();
        order.setUserPhone(user.getPhone());
        order.setGoodsId(goods.getId());
        order.setDeliveryAddrId(0L); // 地址功能待完善
        order.setStatus(0);
        order.setPrice(goods.getPrice());
        order.setCreateDate(new Date());
        goodsService.reduceStock(goods.getId());
        orderDao.insert(order);
        return order;
    }
}
