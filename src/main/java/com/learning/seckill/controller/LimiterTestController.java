package com.learning.seckill.controller;

import com.learning.seckill.limit.Limit;
import com.learning.seckill.result.CodeMsg;
import com.learning.seckill.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LimiterTestController {
    @Limit(maxTokenNum = 2000, createTokenRate = 1500)
    @RequestMapping("/test/limiter")
    @ResponseBody
    public Result<String> limter() {
        return Result.success(CodeMsg.SUCCESS.getMsg());
    }
}
