package com.xiao.rabbitmq.config;

import com.xiao.common.constant.MqConfigConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueuesConfig {

    // 直连模式队列
    @Bean
    public Queue helloQueue(){
        // new Queue("",true);  // 参数1：队列名 参数2：是否持久化
        return new Queue(MqConfigConstant.HELLO_MQ);
    }
    @Bean
    public Queue testQueue(){
        return new Queue(MqConfigConstant.TEST_MQ);
    }

    // 广播模式队列
    @Bean
    public Queue fanoutQueue1() {
        return new Queue(MqConfigConstant.FANOUT_MQ_1);
    }
    @Bean
    public Queue fanoutQueue2() {
        return new Queue(MqConfigConstant.FANOUT_MQ_2);
    }
    @Bean
    public Queue fanoutQueue3() {
        return new Queue(MqConfigConstant.FANOUT_MQ_3);
    }

    // Topic模式队列
    // 支持路由键的模糊匹配 符号“#”匹配一个或多个词，符号“*”匹配不多不少一个词
    @Bean
    public Queue topicQueue1() {
        return new Queue(MqConfigConstant.TOPIC_MQ_1);
    }
    @Bean
    public Queue topicQueue2() {
        return new Queue(MqConfigConstant.TOPIC_MQ_2);
    }

    // 路由声明
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(MqConfigConstant.FANOUT_EXCHANGE);
    }
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(MqConfigConstant.TOPIC_EXCHANGE);
    }

    // 绑定路由 广播模式路由fanoutExchange
    // 绑定到同一个路由下的消息队列会同时收到消息
    @Bean
    public Binding fanoutBinding1(){
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }
    @Bean
    public Binding fanoutBinding2(){
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }
    @Bean
    public Binding fanoutBinding3(){
        return BindingBuilder.bind(fanoutQueue3()).to(fanoutExchange());
    }

    // Topic模式下支持路由键的模糊匹配 符号“#”匹配一个或多个词，符号“*”匹配一个词
    @Bean
    public Binding topicBinding1(){
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with(MqConfigConstant.TOPIC_ROUTING_KEY_1);
    }
    @Bean
    public Binding topicBinding2(){
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with(MqConfigConstant.TOPIC_ROUTING_KEY_2);
    }
}
