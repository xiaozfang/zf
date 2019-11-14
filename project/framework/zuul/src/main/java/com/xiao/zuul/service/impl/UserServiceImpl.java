package com.xiao.zuul.service.impl;

import com.xiao.common.constant.MQConfigConstant;
import com.xiao.common.model.LoginFrom;
import com.xiao.common.model.LoginUser;
import com.xiao.common.response.BaseResponse;
import com.xiao.zuul.feign.UserService;
import com.xiao.zuul.service.IUserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserService userService;
    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Override
    public LoginUser getUserInfo(LoginFrom loginFrom) {
        return userService.login(loginFrom.getUsername(), loginFrom.getPassword());
    }

    @Override
    public BaseResponse register(String userName) {
        rabbitTemplate.convertAndSend(MQConfigConstant.REGISTER_USER, userName);
        return new BaseResponse().success("创建成功");
    }
}
