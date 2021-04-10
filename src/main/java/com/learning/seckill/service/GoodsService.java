package com.learning.seckill.service;

import com.learning.seckill.dao.GoodsDao;
import com.learning.seckill.pojo.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {

    @Autowired
    GoodsDao goodsDao;

    public List<Goods> getGoodsList() {
        return goodsDao.getGoodsList();
    }

    public Goods getGoodsById(Long id) {
        return goodsDao.getGoods(id);
    }

    public Boolean reduceStock(Long id) {
        return goodsDao.reduceStock(id);
    }
}
