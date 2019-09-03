package com.xiao.ordercenter.service;



import com.alibaba.fastjson.JSONObject;
import com.xiao.common.common.CommonMethods;
import com.xiao.common.constant.MQConfigConstant;
import com.xiao.common.constant.MsgLogStatusConstant;
import com.xiao.common.response.BaseResponse;
import com.xiao.dao.entity.MsgLog;
import com.xiao.dao.entity.OrderIdInfo;
import com.xiao.dao.mapper.MsgLogMapper;
import com.xiao.dao.mapper.OrderIdInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class OrderService{
    @Autowired
    private MsgLogMapper msgLogMapper;
    @Autowired
    private OrderIdInfoMapper orderIdInfoMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void addOrderAndDispatch() {
        String msgId = UUID.randomUUID().toString();
        MsgLog msgLog = new MsgLog();
        msgLog.setMsgId(msgId);
        msgLog.setStatus(MsgLogStatusConstant.DELIVERING);
        msgLog.setExchange("test");
        msgLog.setRoutingKey("hello");
        msgLog.setMsg("confirm");
        if (msgLogMapper.insertSelective(msgLog) <= 0) {
            log.info("下单失败!");
        }
        send(msgLog);
    }

    private void send(MsgLog msgLog) {
        String msg = JSONObject.toJSONString(msgLog);
        log.info("msg:" + msg);
        // 封装消息
        Message message = MessageBuilder.withBody(msg.getBytes()).setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setContentEncoding("utf-8").setMessageId(msgLog.getMsgId()).build();
        // 构建回调返回的数据
        CorrelationData correlationData = new CorrelationData(msgLog.getMsgId());
        rabbitTemplate.convertAndSend(MQConfigConstant.FANOUT_EXCHANGE, message, correlationData);
    }

    public String createOrder() {
        String orderId = CommonMethods.generate22UniqueString();
        OrderIdInfo info = new OrderIdInfo();
        info.setOrderId(orderId);
        info.setStatus(0);
        if (orderIdInfoMapper.insert(info) > 0) {
            // 封装消息
            Message message = MessageBuilder.withBody(orderId.getBytes()).setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                    .setContentEncoding("utf-8").setMessageId(orderId).build();
            message.getMessageProperties().setDelay(10000);
            CorrelationData correlationData = new CorrelationData(orderId);
            // 发送延时队列消息
            log.info("发送延时队列消息:" + LocalDateTime.now());
            rabbitTemplate.convertAndSend(MQConfigConstant.EXCHANGE_DELAY, MQConfigConstant.ROUTING_KEY_DELAY_15, message, correlationData);
        }
        return orderId;
    }


    public BaseResponse cancelOrder(String orderId) {
        // 判断订单是否存在
        // 判断订单状态
        // 发布-订阅模式
        CorrelationData correlationData = new CorrelationData(orderId);
        rabbitTemplate.convertAndSend(MQConfigConstant.FANOUT_EXCHANGE,"","hekk", correlationData);
        return null;
    }

    public BaseResponse payOrder(String orderId) {
        return null;
    }
}