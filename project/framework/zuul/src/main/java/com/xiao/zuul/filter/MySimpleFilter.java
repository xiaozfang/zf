package com.xiao.zuul.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import com.xiao.redis.RedisService;
import com.xiao.zuul.pojo.LoginUser;
import com.xiao.zuul.util.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StreamUtils;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        String accessToken = request.getHeader("Authorization");
        if (accessToken == null || "".equals(accessToken)) {
            log.warn("token 为空");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            context.getResponse().setContentType("text/html;charset=UTF-8");
            context.setResponseBody("权限为空，请先<a href='http://localhost:8080/#/'>登录</a>");
        } else {
            Claims claims = JwtUtils.JWTDecode(accessToken);
            if (claims == null) {
                context.setSendZuulResponse(false);
                context.setResponseStatusCode(401);
                context.getResponse().setContentType("text/html;charset=UTF-8");
                context.setResponseBody("用户未登录或签名已失效");
            } else {
                try {
                    Object username = claims.get("username");
                    long ttl = claims.getExpiration().getTime();
                    long now = new Date().getTime();
                    // 验证是否过期, 是否被篡改, 是否已登出
                    if (ttl - now > 0 && username != null && redisService.get("logout_" + username) == null) {
                        InputStream in = request.getInputStream();
                        String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));

                        body = "动态增加一段内容到body中: " + body;
                        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);

                        LoginUser user = new LoginUser();
                        user.setUserid((Integer) claims.get("userid"));
                        user.setUsername(username + "");
                        user.setRoles((List<Integer>) claims.get("roles"));
                        String userJson = JSONObject.toJSONString(user);
                        context.addZuulRequestHeader("loginuser", userJson);
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
