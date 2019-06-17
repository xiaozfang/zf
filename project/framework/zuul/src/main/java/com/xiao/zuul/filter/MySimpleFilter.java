package com.xiao.zuul.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.xiao.redis.RedisService;
import com.xiao.zuul.domain.LoginUser;
import com.xiao.zuul.util.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

@Slf4j
@Configuration
public class MySimpleFilter extends ZuulFilter {
    @Autowired
    private RedisService redisService;

    /**
     * 过滤器的类型 决定过滤器在请求的哪个生命周期中执行
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器的执行顺序
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 判断该过滤器是否需要被执行
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String token = request.getHeader("Authorization");
        if (token == null || "".equals(token)) {
            log.warn("token 为空");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            context.getResponse().setContentType("text/html;charset=UTF-8");
            context.setResponseBody("权限为空，请先<a href='http://localhost:8080/#/'>登录</a>");
        } else {
            Claims claims = JwtUtils.JWTDecode(token);
            if (claims != null) {
                try {
                    int userid = (Integer) claims.get("userid");
                    long ttl = claims.getExpiration().getTime();
                    long now = new Date().getTime();
                    // 验证是否过期, 是否被篡改, 是否已登出
                    if (ttl - now > 0 && userid > 0 && redisService.get("logout_" + userid) == null) {
                        LoginUser user = new LoginUser();
                        user.setUserid(userid);
                        user.setUsername(claims.get("username") + "");
                        user.setRoles((List<Integer>) claims.get("roles"));
                        for (Cookie cookie : request.getCookies()){
                            if ("current_role".equals(cookie.getName())){
                                user.setCurrentrole(Integer.parseInt(cookie.getValue()));
                            }
                        }
                        String userJson = JSONObject.toJSONString(user);
                        context.addZuulRequestHeader("loginuser", URLEncoder.encode(userJson, "UTF-8"));
                        context.setSendZuulResponse(true);
                        context.setResponseStatusCode(200);
                        log.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());
                    } else {
                        log.info("签名过期");
                        context.setSendZuulResponse(false);
                        context.setResponseStatusCode(401);
                        context.getResponse().setContentType("text/html;charset=UTF-8");
                        context.setResponseBody("用户未登录或签名已失效");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                context.setSendZuulResponse(false);
                context.setResponseStatusCode(401);
                context.getResponse().setContentType("text/html;charset=UTF-8");
                context.setResponseBody("用户未登录或签名已失效");
            }
        }
        return null;
    }
}
