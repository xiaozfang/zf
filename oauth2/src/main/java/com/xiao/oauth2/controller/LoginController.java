package com.xiao.oauth2.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {
    @GetMapping({"/", "/index"})
    public String root(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(){

        return "login";
    }

    @RequestMapping("/home")
    public String setCurrentUser(Authentication authentication, HttpServletResponse response){
        authentication.getAuthorities();
//        response
        // 返回菜单，角色列表
        return "home";
    }
}
