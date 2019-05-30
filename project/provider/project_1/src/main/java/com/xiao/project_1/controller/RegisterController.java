package com.xiao.project_1.controller;


import com.xiao.project_1.service.IRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/register")
public class RegisterController {
    @Autowired
    private IRegisterService registerService;

    @GetMapping("/add")
    public String register(String name) {
        log.info("注册信息: " + name);
        return registerService.register(1, "HelloWorld");
    }
}
