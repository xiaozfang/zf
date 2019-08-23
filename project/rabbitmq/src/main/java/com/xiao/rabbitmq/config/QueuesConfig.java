package com.xiao.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class QueuesConfig {

    @Bean
    public Queue helloQueue(){
        return new Queue("hello");
    }
}
