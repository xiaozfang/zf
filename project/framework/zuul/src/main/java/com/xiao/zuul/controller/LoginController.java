package com.xiao.zuul.controller;

import com.xiao.redis.RedisService;
import com.xiao.zuul.config.JwtConfig;
import com.xiao.zuul.dao.UserDao;
import com.xiao.zuul.domain.LoginFrom;
import com.xiao.zuul.pojo.UserInfo;
import com.xiao.zuul.util.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
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
        String username = loginFrom.getUsername();
        String password = loginFrom.getPassword();

        UserInfo userInfo = userDao.getUserByName(username);
        //获取当前登录人的信息 ，如果当前登录人在黑名单中，移除
        if (redisService.get("logout_" + username) != null) {
            redisService.del("logout_" + username);
            log.info("移除黑名单:" + username);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("username", "xzf");
        map.put("userid", 100);
        map.put("roles", Arrays.asList(100,101,102));
        // 2 生成jwt
        String jwt = JwtUtils.createJWT(map, JwtConfig.JWT_TTL);
        // 3 cookie设置默认角色
        Cookie current_role = new Cookie("current_role","100");
        response.addCookie(current_role);
        response.setHeader("Authorization", jwt);
        return "success";
    }

    @GetMapping("/logout")
    public String logout(@RequestParam String username, HttpServletRequest request) {
        Claims claims = JwtUtils.JWTDecode(request.getHeader("Authorization"));
        if (claims == null){
            log.info("用户未登录，或签名已失效");
            return "用户未登录，或签名已失效";
        } else {
            long ttl = claims.getExpiration().getTime();
            long now = new Date().getTime();
            if (now - ttl > 0){
                //维持一个黑名单，当用户调用这个接口的时候，将用户加入黑名单，等待签名过期
                redisService.set("logout_" + username,username, now - ttl);
            }
            log.info("退出登录成功");
            return "退出登录成功！";
        }
    }

    @RequestMapping("/home")
    public void setCurrentUser() {

    }
}
