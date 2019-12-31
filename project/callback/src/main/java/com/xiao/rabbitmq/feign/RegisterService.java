package com.xiao.rabbitmq.feign;

import com.xiao.common.response.BaseResponse;
import com.xiao.rabbitmq.feign.fallback.CommonFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 远测调用登录接口
 */
@Service
@FeignClient(name = "usercenter", fallback = CommonFallBack.class, decode404 = true)
public interface RegisterService {
    /**
     * 注册登录接口
     */
    @GetMapping(value = "/api/user/register")
    BaseResponse register(@RequestParam("username") String username);
}
