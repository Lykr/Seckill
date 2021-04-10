package com.learning.seckill.controller;

import com.learning.seckill.pojo.Goods;
import com.learning.seckill.pojo.Order;
import com.learning.seckill.pojo.User;
import com.learning.seckill.redis.RedisService;
import com.learning.seckill.redis.prefix.UserPrefix;
import com.learning.seckill.result.CodeMsg;
import com.learning.seckill.service.GoodsService;
import com.learning.seckill.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class OrderController {
    Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    RedisService redisService;

    @PostMapping("/seckill")
    public String secKill(Model model, @CookieValue(value = "token", required = false) String token, @RequestParam("goodsId") Long goodsId) {
        User user = redisService.get(UserPrefix.GET_BY_TOKEN, token, User.class);
        if (user == null) return "redirect:/login";

        model.addAttribute("user", user);

        //判断库存
        Goods goods = goodsService.getGoodsById(goodsId);
        int stock = goods.getStock();
        if (stock <= 0) {
            model.addAttribute("errmsg", CodeMsg.MIAO_SHA_OVER.getMsg());
            return "seckill_fail";
        }

        //判断是否已经秒杀到了
        Order order = orderService.getByUserIdGoodsId(user.getPhone(), goodsId);
        if (order != null) {
            model.addAttribute("errmsg", CodeMsg.REPEATE_MIAOSHA.getMsg());
            return "seckill_fail";
        }

        //减库存 下订单 写入秒杀订单
        order = orderService.secKill(user, goods);
        model.addAttribute("order", order);
        model.addAttribute("goods", goods);
        return "order_detail";
    }
}
