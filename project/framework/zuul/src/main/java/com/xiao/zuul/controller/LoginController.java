package com.xiao.zuul.controller;

import com.xiao.redis.RedisService;
import com.xiao.zuul.config.JwtConfig;
import com.xiao.zuul.dao.UserDao;
import com.xiao.zuul.domain.LoginFrom;
import com.xiao.zuul.pojo.UserInfo;
import com.xiao.zuul.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisService redisService;

    @PostMapping("/login")
    public String login(@RequestBody LoginFrom loginFrom, HttpServletResponse response) {
        // 1 获取当前登录人的信息
        UserInfo userInfo;
        Object obj = redisService.get(loginFrom.getUsername());
        if (obj != null){
            userInfo = (UserInfo) obj;
            log.info("命中缓存" + userInfo.toString());
        } else {
            userInfo = userDao.getUserByName(loginFrom.getUsername());
            if (userInfo != null){
                redisService.set(loginFrom.getUsername(), userInfo);
                log.info("缓存" + userInfo.toString());
            }
        }
        Map<String, Object> map = new HashMap<>();
        // 2 生成jwt
        String jwt = JwtUtils.createJWT(map, JwtConfig.JWT_TTL);
        // 3 cookie设置默认角色
        response.addCookie(new Cookie("current_role", ""));
        response.setHeader("Authorization", jwt);
        return "success";
    }

    @GetMapping("/logout")
    public String logout() {

        return "login";
    }

    @RequestMapping("/home")
    public void setCurrentUser() {

    }
}
