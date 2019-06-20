package com.xiao.zuul.controller;

import com.alibaba.fastjson.JSONObject;
import com.xiao.common.model.LoginFrom;
import com.xiao.common.model.LoginUser;
import com.xiao.common.model.RoleBaseInfo;
import com.xiao.common.response.BaseResponse;
import com.xiao.redis.RedisService;
import com.xiao.zuul.config.JwtConfig;
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
    public BaseResponse login(@RequestBody LoginFrom loginFrom, HttpServletResponse response) {

        String username = loginFrom.getUsername();
        String password = loginFrom.getPassword();
        if (username == null || password == null) {
            return new BaseResponse().fail("用户名，密码不能为空");
        }
        LoginUser user = userService.getUserInfo(loginFrom);
        if (user == null) {
            return new BaseResponse().fail("用户名/密码错误");
        }
        int userid = user.getUserid();
        //获取当前登录人的信息 ，如果当前登录人在黑名单中，移除
        if (redisService.get("logout_" + userid) != null) {
            redisService.del("logout_" + userid);
            log.info("移除黑名单:" + userid);
        }
        List<RoleBaseInfo> roles = user.getRoles();
        Map<String, Object> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("userid", userid);
        map.put("roles", JSONObject.toJSONString(roles));
        // 2 生成jwt
        String jwt = JwtUtils.createJWT(map, JwtConfig.JWT_TTL);
        // 3 cookie设置默认角色
        Cookie current_role = new Cookie("current_role", "");
        current_role.setDomain("fang.com");
        current_role.setPath("/");
        current_role.setMaxAge(60);
        if (roles != null && roles.size() > 0) {
            // 取第一个角色
            current_role.setValue(roles.get(0).getRoleid() + "");
        } else {
            // 如果没有角色，设置一个默认角色
            current_role.setValue("10");
        }
        response.addCookie(current_role);
        response.setHeader("Authorization", jwt);
        return new BaseResponse().success("登录成功");
    }

    @GetMapping("/logout")
    public BaseResponse logout(HttpServletRequest request, HttpServletResponse response) {
        Claims claims = JwtUtils.JWTDecode(request.getHeader("Authorization"));
        if (claims == null) {
            return new BaseResponse().fail("用户未登录，或签名已失效");
        } else {
            int userid = (int) claims.get("userid");
            long ttl = claims.getExpiration().getTime();
            long now = new Date().getTime();
            if (ttl - now > 0) {
                //维持一个黑名单，当用户调用这个接口的时候，将用户加入黑名单，等待签名过期
                redisService.set("logout_" + userid, userid, ttl - now);
            }
            response.reset();
            return new BaseResponse().success("退出登录成功");
        }
    }
}
