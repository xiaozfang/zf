package com.xiao.rabbitmq.service;

import com.rabbitmq.client.Channel;
import com.xiao.common.constant.MQConfigConstant;
import com.xiao.dao.entity.OrderIdInfo;
import com.xiao.dao.mapper.OrderIdInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class DelayQueueService {
    @Autowired
    private OrderIdInfoMapper orderIdInfoMapper;

    @RabbitListener(queues = MQConfigConstant.DELAY_MQ_15M)
    public void overtimeCancelOrder(Channel channel, Message message) throws IOException {
        String orderId = new String(message.getBody());
        long tagId = message.getMessageProperties().getDeliveryTag();
        log.info("DELAY_MQ_15M 接收到消息:" + orderId);
        OrderIdInfo info = orderIdInfoMapper.selectOrderIdInfoByOrderId(orderId);
        if (info == null) {
            log.error("orderId错误" + orderId);
            channel.basicReject(tagId, false);
        } else {
            if (info.getStatus() == 0) {
                orderIdInfoMapper.updateStatusByOrderId(orderId, 3);
                channel.basicAck(tagId, false);
            } else {
                log.error("orderId状态错误" + info.getStatus());
                channel.basicNack(tagId, false, false);
            }
        }
    }
}
