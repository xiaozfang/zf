package com.xiao.zuul.controller;

import com.xiao.common.response.BaseResponse;
import com.xiao.zuul.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping()
public class RegisterController {
    @Autowired
    private IUserService userService;

    @GetMapping("/register")
    public BaseResponse register() {
        BaseResponse response = new BaseResponse();
        for (int i = 0; i < 5000; i++) {
            response = userService.register("用户" + i);
            log.info("用户" + i + " " + response.getMessage());
        }
        return response;
    }
}
