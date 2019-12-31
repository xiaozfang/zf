package com.xiao.rabbitmq.service;

import com.rabbitmq.client.Channel;
import com.xiao.common.constant.MQConfigConstant;
import com.xiao.common.response.BaseResponse;
import com.xiao.rabbitmq.feign.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RegisterReceiver {
    @Autowired
    RegisterService registerService;

    @RabbitListener(queues = MQConfigConstant.REGISTER_USER)
    public void register(String userName, Message message, Channel channel) {
        log.info("收到消息:{}", userName);
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            BaseResponse response = registerService.register(userName);
            if (response.getCode() == 1){
                channel.basicAck(deliveryTag, false);
            } else {
                channel.basicReject(deliveryTag, false);
                throw new Exception(response.getMessage());
            }

        } catch (Exception e) {
            log.error("队列处理时发生异常:{}", e.getMessage());
            // 记录处理失败的消息到数据库
        }
    }
}
