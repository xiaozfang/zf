package com.xiao.rabbitmq.service;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.xiao.dao.entity.MsgLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class TopicQueueReceiver {
//    @RabbitListener(queues = MQConfigConstant.TOPIC_MQ_1)
    public void topicQueue1(Channel channel, Message message){
        log.info("TOPIC_MQ_1接收到消息:" + new String(message.getBody()));
    }

//    @RabbitListener(queues = MQConfigConstant.TOPIC_MQ_2)
    public void topicQueue2(Channel channel, Message message){
        MsgLog msgLog = JSONObject.parseObject(message.getBody(), MsgLog.class);
        //拒绝消息 消息会被丢弃，不会重回队列
        try {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("TOPIC_MQ_2接收到消息:" + msgLog.getMsgId());
    }
}
