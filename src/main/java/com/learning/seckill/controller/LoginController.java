package com.learning.seckill.controller;

import com.learning.seckill.result.Result;
import com.learning.seckill.redis.RedisService;
import com.learning.seckill.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.learning.seckill.vo.LoginVo;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController {
    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/to-login")
    public String toLogin() {
        return "login";
    }

    @ResponseBody
    @RequestMapping("/do-login")
    public Result<String> doLogin(HttpServletResponse response, LoginVo loginVo) {
        log.info("User [{}] login...", loginVo.toString());
        String token = userService.login(response, loginVo);
        log.info("User [{}] login successfully!", loginVo);
        return Result.success(token);
    }
}
