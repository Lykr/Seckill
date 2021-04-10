package com.learning.seckill.service;

import com.learning.seckill.dao.UserDao;
import com.learning.seckill.exception.SecKillException;
import com.learning.seckill.pojo.User;
import com.learning.seckill.redis.RedisService;
import com.learning.seckill.redis.prefix.UserPrefix;
import com.learning.seckill.result.CodeMsg;
import com.learning.seckill.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    RedisService redisService;

    public User getByPhone(Long phone) {
        User user;
        // 尝试从缓存中获取
        user = redisService.get(UserPrefix.GET_BY_PHONE, String.valueOf(phone), User.class);
        // 从数据库中获取
        if (user == null) {
            user = userDao.getByPhone(phone);
            if (user != null) redisService.set(UserPrefix.GET_BY_PHONE, String.valueOf(phone), user);
        }
        return user;
    }

    public String login(HttpServletResponse response, LoginVo loginVo) {
        // 判断是否存在输入
        if (loginVo == null) throw new SecKillException(CodeMsg.SERVER_ERROR);

        // 获取用户输入的手机号和密码
        Long phone = loginVo.getPhone();
        String password = loginVo.getPassword();

        // 根据 token 获取用户
        User user = getByPhone(phone);
        if (user == null) throw new SecKillException(CodeMsg.MOBILE_NOT_EXIST);

        // 验证密码
        String passwordFromDB = user.getPassword();
        if (!passwordFromDB.equals(password)) throw new SecKillException(CodeMsg.PASSWORD_ERROR);

        // 为用户生成 token
        String token = UUID.randomUUID().toString();
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        response.addCookie(cookie);

        // 缓存 token
        redisService.set(UserPrefix.GET_BY_TOKEN, token, user);

        return token;
    }
}
