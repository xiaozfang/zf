package com.xiao.rabbitmq.config;

import com.xiao.common.constant.MQConfigConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class QueuesConfig {
    /*****************************************声明队列***********************************************/

    // 直连模式队列
    @Bean
    public Queue helloQueue() {
        // new Queue("",true);  // 参数1：队列名 参数2：是否持久化
        return new Queue(MQConfigConstant.HELLO_MQ);
    }

    @Bean
    public Queue testQueue() {
        return new Queue(MQConfigConstant.TEST_MQ);
    }
    @Bean
    public Queue registerUser() {
        return new Queue(MQConfigConstant.REGISTER_USER);
    }
    // 广播模式队列

    @Bean
    public Queue fanoutQueue1() {
        return new Queue(MQConfigConstant.FANOUT_MQ_1);
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue(MQConfigConstant.FANOUT_MQ_2);
    }

    @Bean
    public Queue fanoutQueue3() {
        return new Queue(MQConfigConstant.FANOUT_MQ_3);
    }

    // Topic模式队列
    @Bean
    public Queue topicQueue1() {
        return new Queue(MQConfigConstant.TOPIC_MQ_1);
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue(MQConfigConstant.TOPIC_MQ_2);
    }

    @Bean
    public Queue delayQueueWait15min(){
        return new Queue(MQConfigConstant.DELAY_MQ_15M);
    }


    /*****************************************定义路由***********************************************/
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(MQConfigConstant.FANOUT_EXCHANGE);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(MQConfigConstant.TOPIC_EXCHANGE);
    }

    // 延时消息队列路由
    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(MQConfigConstant.EXCHANGE_DELAY, "x-delayed-message", true, false, args);
    }


    /*****************************************绑定路由***********************************************/
    /**
     * 广播模式路由fanoutExchange 绑定到同一个路由下的消息队列会同时收到消息
     * 可以实现发布订阅功能，该模式没有RoutingKey和BindingKey，Exchange直接将消息发送给所有与之绑定的所有队列，消费者监听指定的队列，消费队列中的消息
     */

    @Bean
    public Binding fanoutBinding1() {
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutBinding2() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutBinding3() {
        return BindingBuilder.bind(fanoutQueue3()).to(fanoutExchange());
    }



    /**
     * 主题模式类似于直连模式，只是直连模式是精准匹配，主题模式是模糊匹配
     * Topic模式下支持路由键的模糊匹配 符号“#”匹配一个或多个词，符号“*”匹配一个词
     */
    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with(MQConfigConstant.TOPIC_ROUTING_KEY_1);
    }

    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with(MQConfigConstant.TOPIC_ROUTING_KEY_2);
    }



    @Bean
    public Binding delayBinding() {
        return BindingBuilder.bind(delayQueueWait15min()).to(delayExchange()).with(MQConfigConstant.ROUTING_KEY_DELAY_15).noargs();
    }
}
