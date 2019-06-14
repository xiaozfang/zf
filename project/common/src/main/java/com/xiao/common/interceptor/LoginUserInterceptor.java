package com.xiao.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.xiao.common.context.LoginUserContext;
import com.xiao.common.model.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

@Slf4j
@Component
public class LoginUserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("context: " + request.getContextPath());
        log.info("url: " + request.getRequestURI());
        log.info("servletPath: " + request.getServletPath());
        String servletPath = request.getServletPath();
        // 登录/注册接口没有用户信息
        if ("/api/user/login".equals(servletPath) || "/api/user/register".equals(servletPath)){
            return true;
        }
        String loginStr = URLDecoder.decode(request.getHeader("loginuser"), "UTF-8");
        LoginUser user = JSONObject.parseObject(loginStr, LoginUser.class);
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
