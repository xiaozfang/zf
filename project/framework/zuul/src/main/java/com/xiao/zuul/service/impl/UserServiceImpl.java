package com.xiao.zuul.service.impl;

import com.xiao.common.constant.MQConfigConstant;
import com.xiao.common.model.LoginFrom;
import com.xiao.common.model.LoginUser;
import com.xiao.common.response.BaseResponse;
import com.xiao.zuul.feign.UserService;
import com.xiao.zuul.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
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
        CorrelationData data = new CorrelationData(userName+"Ss");
        if (userName.contains("8")){
//            rabbitTemplate.convertAndSend(MQConfigConstant.HELLO_MQ, (Object) userName, data);
            rabbitTemplate.convertAndSend("MQConfigConstant.REGISTER_USER", (Object) userName);

        } else {
            rabbitTemplate.convertAndSend(MQConfigConstant.REGISTER_USER, (Object) userName, data);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info(userName +"创建成功");
        }

        return new BaseResponse().success("创建成功");
    }
}
