package com.xiao.common.interceptor;

import com.xiao.common.constant.CommonConstant;
import com.xiao.common.context.LoginUserContext;
import com.xiao.common.model.LoginUser;
import com.xiao.common.util.JwtUtils;
import com.xiao.common.util.StringTools;
import com.xiao.redis.RedisService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class LoginUserInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisService redisService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        LoginUser user = (LoginUser) request.getAttribute("loginuser");
        if (user == null) {
            log.info("请先登录");
            return false;
        } else {
            LoginUserContext.setLoginUser(user);
            return true;
        }
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoginUserContext.removeLoginUser();
    }
}
