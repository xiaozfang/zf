package com.xiao.rabbitmq.service;

import com.rabbitmq.client.Channel;
import com.xiao.common.constant.MQConfigConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FanoutQueueReceiver {
    @RabbitListener(queues = MQConfigConstant.FANOUT_MQ_1)
    public void fanoutQueue1(Channel channel, Message message){
        log.info("FANOUT_MQ_1接收到消息:" + new String(message.getBody()));
    }

    @RabbitListener(queues = MQConfigConstant.FANOUT_MQ_2)
    public void fanoutQueue2(Channel channel, Message message){
        log.info("FANOUT_MQ_2接收到消息:" + message.getMessageProperties().getContentLength());
    }

    @RabbitListener(queues = MQConfigConstant.FANOUT_MQ_3)
    public void fanoutQueue3(Channel channel, Message message){
        log.info("FANOUT_MQ_3接收到消息:" + channel.toString());
    }
}
