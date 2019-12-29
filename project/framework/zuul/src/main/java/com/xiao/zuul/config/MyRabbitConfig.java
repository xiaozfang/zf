package com.xiao.zuul.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MyRabbitConfig implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
        return rabbitTemplate;
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("消息成功发送到RabbitClient, CorrelationData:{}, s:{}", correlationData, cause);
        } else {
            log.info("消息发送到RabbitClient失败, CorrelationData:{}, s:{}", correlationData, cause);
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.error("Fail... message:{},从交换机exchange:{},以路由键routingKey:{},未找到匹配队列，replyCode:{},replyText:{}", message, exchange, routingKey, replyCode, replyText);
    }
}
