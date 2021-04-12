package com.learning.seckill.controller;

import com.learning.seckill.redis.RedisService;
import com.learning.seckill.result.Result;
import com.learning.seckill.service.UserService;
import com.learning.seckill.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController {
    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @GetMapping
    public String toLogin(@CookieValue(value = "token", required = false) String token) {
        if (token != null) return "redirect:/goods/list";
        return "login";
    }

    @ResponseBody
    @PostMapping
    public Result<String> doLogin(@CookieValue(value = "token", required = false) String tokenInput, HttpServletResponse response, LoginVo loginVo) {
        log.info("User [{}] login...", loginVo.toString());
        String token = userService.login(tokenInput, response, loginVo);
        log.info("User [{}] login successfully!", loginVo);
        return Result.success(token);
    }
}
