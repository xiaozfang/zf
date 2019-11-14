package com.xiao.rabbitmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableRabbit
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@ComponentScan(basePackages = "com.xiao")
public class CallBackApplication {
    public static void main(String[] args) {
        SpringApplication.run(CallBackApplication.class, args);
    }
}
