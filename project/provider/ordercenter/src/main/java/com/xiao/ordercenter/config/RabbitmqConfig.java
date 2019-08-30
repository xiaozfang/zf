package com.xiao.ordercenter.config;//package com.xiao.ordercenter.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.support.CorrelationData;
//import org.springframework.context.annotation.Configuration;
//
//@Slf4j
//@Configuration
//public class QueueConfig implements RabbitTemplate.ConfirmCallback {
//
//    @Override
//    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//        String msgId = correlationData.getId();
//        if (ack) {
//            log.info("消息唯一标识:{}\t状态:{}", msgId, "SUCCESS");
//        } else {
//            log.info("消息唯一标识:{}\t状态:{}\t原因:{}", msgId, "FAIL", cause);
//        }
//    }
//}
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqConfig {

    // 下单并且派单存队列
    public static final String ORDER_DIC_QUEUE = "order_dic_queue";
    // 补单队列，判断订单是否已经被创建
    public static final String ORDER_CREATE_QUEUE = "order_create_queue";
    // 下单并且派单交换机
    private static final String ORDER_EXCHANGE_NAME = "order_exchange_name";

    // 1.定义订单队列
    @Bean
    public Queue directOrderDicQueue() {
        return new Queue(ORDER_DIC_QUEUE);
    }

    // 2.定义补订单队列
    @Bean
    public Queue directCreateOrderQueue() {
        return new Queue(ORDER_CREATE_QUEUE);
    }

    // 2.定义交换机
    @Bean
    DirectExchange directOrderExchange() {
        return new DirectExchange(ORDER_EXCHANGE_NAME);
    }

    // 3.订单队列与交换机绑定
    @Bean
    Binding bindingExchangeOrderDicQueue() {
        return BindingBuilder.bind(directOrderDicQueue()).to(directOrderExchange()).with("orderRoutingKey");
    }

    // 3.补单队列与交换机绑定
    @Bean
    Binding bindingExchangeCreateOrder() {
        return BindingBuilder.bind(directCreateOrderQueue()).to(directOrderExchange()).with("orderRoutingKey");
    }

}