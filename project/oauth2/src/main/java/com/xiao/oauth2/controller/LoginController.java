//package com.xiao.oauth2.controller;
//
//import com.xiao.common.response.BaseResponse;
//import com.xiao.oauth2.config.JwtConfig;
//import com.xiao.oauth2.service.JwtService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//public class LoginController {
//    @Autowired
//    private JwtService jwtService;
//
//    @GetMapping({"/", "/index"})
//    public String root(){
//        return "index";
//    }
//
//    @GetMapping("/login")
//    public String login(){
//        return "login";
//    }
//
//    @GetMapping("/logout")
//    public String logout(){
//
//        return "login";
//    }
//
//    @RequestMapping("/home")
//    public BaseResponse setCurrentUser(Authentication auth, HttpServletResponse response){
//
//        // 1 获取当前登录人的信息
//        auth.getAuthorities();
//        Map<String, Object> map = new HashMap<>();
//        // 2 生成jwt
//        String jwt = jwtService.createJWT(map, JwtConfig.JWT_TTL);
//        // 3 cookie设置默认角色
//        response.addCookie(new Cookie("current_role", ""));
//        response.setHeader("Authorization", jwt);
//        return new BaseResponse().SUCCESS();
//    }
//}
