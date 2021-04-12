package com.learning.seckill.redis.prefix;

public class GoodsPrefix extends BasePrefix {

    private GoodsPrefix(String prefix) {
        super(prefix, -1L);
    }

    public static GoodsPrefix GOODS_ID_TO_STOCK = new GoodsPrefix("goodsId2stock:");
}
