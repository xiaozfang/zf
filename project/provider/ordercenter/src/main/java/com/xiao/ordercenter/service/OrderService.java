package com.xiao.ordercenter.service;



import com.alibaba.fastjson.JSONObject;
import com.xiao.common.constant.MsgLogStatusConstant;
import com.xiao.dao.entity.MsgLog;
import com.xiao.dao.mapper.MsgLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class OrderService implements RabbitTemplate.ConfirmCallback {
    @Autowired
    private MsgLogMapper msgLogMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void addOrderAndDispatch() {
        //先下单 订单表插入数据
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
        // 2.订单表插插入完数据后 订单表发送 外卖小哥
        send(msgLog);
        //    int i = 1/0;   //发生异常
    }

    private void send(MsgLog msgLog) {

        String msg = JSONObject.toJSONString(msgLog);
        log.info("msg:" + msg);
        // 封装消息
        Message message = MessageBuilder.withBody(msg.getBytes()).setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setContentEncoding("utf-8").setMessageId(msgLog.getMsgId()).build();
        // 构建回调返回的数据
        CorrelationData correlationData = new CorrelationData(msgLog.getMsgId());
        // 发送消息
        this.rabbitTemplate.setMandatory(true);
        this.rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.convertAndSend("order_exchange_name", "orderRoutingKey", message, correlationData);

    }

    // 生产消息确认机制 生产者往服务器端发送消息的时候 采用应答机制
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String orderId = correlationData.getId(); //id 都是相同的哦  全局ID
        System.out.println("消息id:" + correlationData.getId());
        if (ack) { //消息发送成功
            System.out.println("消息发送确认成功");
        } else {
            //重试机制
//            send(orderId);
            System.out.println("消息发送确认失败:" + cause);
        }

    }

}