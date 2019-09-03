package com.xiao.rabbitmq.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    /**
     * 消费者数量，默认10
     */
    private static final int DEFAULT_CONCURRENT = 10;

    /**
     * 每个消费者获取最大投递数量 默认50
     */
    private static final int DEFAULT_PREFETCH_COUNT = 50;

    /**
     * 配置消息消费者多线程消费
     * 使用: 在@RabbitListener(queues=queueName, containerFactory = pointTaskContainerFactory)
     */
    @Bean("pointTaskContainerFactory")
    public SimpleRabbitListenerContainerFactory pointTaskContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setPrefetchCount(DEFAULT_CONCURRENT);
        factory.setConcurrentConsumers(DEFAULT_PREFETCH_COUNT);
        configurer.configure(factory, connectionFactory);
        return factory;
    }
}
