package com.xiao.zuul.service.impl;

import com.xiao.common.model.LoginFrom;
import com.xiao.common.model.LoginUser;
import com.xiao.zuul.feign.UserService;
import com.xiao.zuul.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserService userService;


    @Override
    public LoginUser getUserInfo(LoginFrom loginFrom) {
        return userService.login(loginFrom.getUsername(), loginFrom.getPassword());
    }
}
