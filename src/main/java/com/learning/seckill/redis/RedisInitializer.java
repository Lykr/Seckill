package com.learning.seckill.redis;

import com.learning.seckill.pojo.Goods;
import com.learning.seckill.redis.prefix.GoodsPrefix;
import com.learning.seckill.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RedisInitializer implements ApplicationRunner {
    Logger log = LoggerFactory.getLogger(RedisInitializer.class);

    @Autowired
    RedisService redisService;

    @Autowired
    GoodsService goodsService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 获取所有商品
        List<Goods> goodsList = goodsService.getGoodsList();

        // 缓存所有商品
        for (Goods goods : goodsList) {
            redisService.set(GoodsPrefix.GOODS_ID_TO_STOCK, String.valueOf(goods.getId()), goods.getStock());
        }

        log.info("Redis initializing over.");
    }
}
