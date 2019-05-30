package com.xiao.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.xiao.zuul.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Configuration
public class MySimpleFilter extends ZuulFilter {
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
        log.info("send {} request to {}", request.getMethod () ,request.getRequestURL().toString());
        String accessToken = request.getHeader("Authorization");
        if (accessToken == null){
            log.warn("access_token 为空");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            context.getResponse().setContentType("text/html;charset=UTF-8");
            context.setResponseBody("权限为空，请先<a href='http://localhost:8080/#/'>登录</a>");
        } else {
            // 验证是否过期，是否被篡改
            if (!JwtUtils.checkJWT(accessToken)){
                log.info("签名过期");
                context.setSendZuulResponse(false);
                context.setResponseStatusCode(401);
                context.getResponse().setContentType("text/html;charset=UTF-8");
                context.setResponseBody("签名过期，请先<a href='http://localhost:8080/#/'>登录</a>");
            } else {
                log.info("access_token ok");
                context.setSendZuulResponse(true);
                context.setResponseStatusCode(200);
            }
        }
        return null;
    }
}
