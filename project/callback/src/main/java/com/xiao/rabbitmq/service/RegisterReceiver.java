package com.xiao.rabbitmq.service;

import com.rabbitmq.client.Channel;
import com.xiao.common.constant.MQConfigConstant;
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
            if (!userName.contains("7")) {
                registerService.register(userName);
                channel.basicAck(deliveryTag, false);
            } else {
                channel.basicNack(deliveryTag, false, true);
                throw new RuntimeException("错误类型的客户" + userName);
            }
        } catch (Exception e) {
            log.error("队列处理时发生异常:{}", e.getMessage());
        }
    }
}
