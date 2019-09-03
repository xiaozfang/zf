package com.xiao.ordercenter.config;

import com.xiao.common.constant.MsgLogStatusConstant;
import com.xiao.dao.entity.MsgLog;
import com.xiao.dao.mapper.MsgLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class RabbitTemplateConfig implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MsgLogMapper msgLogMapper;

    @PostConstruct
    public void init() {
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(this);            //指定 ConfirmCallback
        rabbitTemplate.setReturnCallback(this);            //指定 ReturnCallback
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        MsgLog msgLog = new MsgLog();
        String msgId = correlationData.getId();
        msgLog.setMsgId(msgId);
        if (ack) {
            log.info("消息唯一标识:{}\t状态:{}", msgId, "SUCCESS");
            msgLog.setStatus(MsgLogStatusConstant.DELIVER_SUCCESS);
//            msgLogMapper.updateByPrimaryKeySelective(msgLog);
        } else {
            log.info("消息唯一标识:{}\t状态:{}\t原因:{}", msgId, "FAIL", cause);
//            msgLog.setStatus(MsgLogStatusConstant.DELIVER_FAIL);
//            msgLogMapper.updateByPrimaryKeySelective(msgLog);
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("消息主体 message : " + message);
        log.info("消息主体 message : " + replyCode);
        log.info("描述：" + replyText);
        log.info("消息使用的交换器 exchange : " + exchange);
        log.info("消息使用的路由键 routing : " + routingKey);
    }
}
