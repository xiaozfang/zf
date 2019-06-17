package com.xiao.zuul.controller;

import com.xiao.redis.RedisService;
import com.xiao.zuul.config.JwtConfig;
import com.xiao.zuul.domain.LoginFrom;
import com.xiao.zuul.domain.LoginUser;
import com.xiao.zuul.domain.ResponseBase;
import com.xiao.zuul.service.IUserService;
import com.xiao.zuul.util.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private IUserService userService;
    @Autowired
    private RedisService redisService;

    @PostMapping("/login")
    public ResponseBase login(@RequestBody LoginFrom loginFrom, HttpServletResponse response) {

        String username = loginFrom.getUsername();
        String password = loginFrom.getPassword();
        if (username == null || password == null) {
            return new ResponseBase().fail("用户名，密码不能为空");
        }
        LoginUser user = userService.getUserInfo(loginFrom);
        if (user == null) {
            return new ResponseBase().fail("用户名/密码错误");
        }
        //获取当前登录人的信息 ，如果当前登录人在黑名单中，移除
        if (redisService.get("logout_" + username) != null) {
            redisService.del("logout_" + username);
            log.info("移除黑名单:" + username);
        }
        List<Integer> roles = user.getRoles();
        Map<String, Object> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("userid", user.getUserid());
        map.put("roles", roles);
        // 2 生成jwt
        String jwt = JwtUtils.createJWT(map, JwtConfig.JWT_TTL);
        // 3 cookie设置默认角色
        if (roles != null && roles.size() > 0) {
            Cookie current_role = new Cookie("current_role", user.getRoles().get(0) + "");
            response.addCookie(current_role);
        }
        response.setHeader("Authorization", jwt);
        return new ResponseBase().success("登录成功");
    }

    @GetMapping("/logout")
    public ResponseBase logout(HttpServletRequest request, HttpServletResponse response) {
        Claims claims = JwtUtils.JWTDecode(request.getHeader("Authorization"));
        if (claims == null) {
            return new ResponseBase().fail("用户未登录，或签名已失效");
        } else {
            int userid = (int) claims.get("userid");
            long ttl = claims.getExpiration().getTime();
            long now = new Date().getTime();
            if (ttl - now > 0) {
                //维持一个黑名单，当用户调用这个接口的时候，将用户加入黑名单，等待签名过期
                redisService.set("logout_" + userid, userid, ttl - now);
            }
            response.reset();
            return new ResponseBase().success("退出登录成功");
        }
    }
}
