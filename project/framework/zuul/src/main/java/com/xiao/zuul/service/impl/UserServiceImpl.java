package com.xiao.zuul.service.impl;

import com.xiao.zuul.domain.LoginFrom;
import com.xiao.zuul.feign.LoginService;
import com.xiao.zuul.domain.LoginUser;
import com.xiao.zuul.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private LoginService loginService;


    @Override
    public LoginUser getUserInfo(LoginFrom loginFrom) {
        return loginService.login(loginFrom.getUsername(), loginFrom.getPassword());
    }
}
