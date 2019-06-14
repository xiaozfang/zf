package com.xiao.zuul.feign;

import com.xiao.zuul.feign.fallback.CommonFallBack;
import com.xiao.zuul.pojo.LoginUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 远测调用登录接口
 */
@Service
@FeignClient(name = "usercenter", fallback = CommonFallBack.class, decode404 = true)
public interface LoginService {
    /**
     * 用户登录接口
     */
    @GetMapping(value = "/api/user/login")
    LoginUser login(@RequestParam("username") String username, @RequestParam("password") String password);
}
