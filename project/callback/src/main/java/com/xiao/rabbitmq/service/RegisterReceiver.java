package com.xiao.rabbitmq.service;

import com.xiao.common.constant.MQConfigConstant;
import com.xiao.rabbitmq.feign.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RegisterReceiver {
    @Autowired
    RegisterService registerService;

    @RabbitListener(queues = MQConfigConstant.REGISTER_USER, containerFactory = "pointTaskContainerFactory")
    public void register(String userName) {
        log.info("收到消息:{}" , userName);
        registerService.register(userName);
    }
}
