package com.learning.seckill.controller;

import com.learning.seckill.pojo.Goods;
import com.learning.seckill.pojo.User;
import com.learning.seckill.redis.RedisService;
import com.learning.seckill.redis.prefix.UserPrefix;
import com.learning.seckill.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    RedisService redisService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Goods> list = goodsService.getGoodsList();
        model.addAttribute("goodsList", list);
        return "goods_list";
    }

    @GetMapping("/detail/{goodsId}")
    public String detail(Model model, @CookieValue(value = "token", required = false) String token, @PathVariable("goodsId") Long goodsId) {
        if (token == null) return "redirect:/login";
        User user = redisService.get(UserPrefix.TOKEN_TO_USER, token, User.class);
        model.addAttribute("user", user);

        Goods goods = goodsService.getGoodsById(goodsId);
        model.addAttribute("goods", goods);

        long startAt = goods.getStartDate().getTime();
        long endAt = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int miaoshaStatus = 0;
        int remainSeconds = 0;
        if (now < startAt) {//秒杀还没开始，倒计时
            miaoshaStatus = 0;
            remainSeconds = (int) ((startAt - now) / 1000);
        } else if (now > endAt) {//秒杀已经结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        } else {//秒杀进行中
            miaoshaStatus = 1;
            remainSeconds = 0;
        }

        model.addAttribute("miaoshaStatus", miaoshaStatus);
        model.addAttribute("remainSeconds", remainSeconds);

        return "goods_detail";
    }
}
