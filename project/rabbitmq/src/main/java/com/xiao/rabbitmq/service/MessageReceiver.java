package com.xiao.rabbitmq.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @RabbitListener 可以标注在类上面(接收所有hello队列中的消息)，需配合 @RabbitHandler 注解一起使用(根据参数进行匹配)
 * @RabbitListener 标注在类上面表示当有收到消息的时候，就交给 @RabbitHandler 的方法处理，具体使用哪个方法处理，根据 MessageConverter 转换后的参数类型
 */
@Slf4j
@Component
@RabbitListener(queues = "hello")
public class MessageReceiver {

    @RabbitHandler
    public void helloReceiver(String msg){
        log.info("msg" + msg);
    }

    @RabbitHandler
    public void helloReceiver(byte [] msg){
        log.info("msg1" + new String(msg));
    }
}
