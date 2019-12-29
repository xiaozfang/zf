package com.xiao.rabbitmq.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RabbitConfig {
    /**
     * 消费者数量，默认10
     */
    private static final Integer DEFAULT_CONCURRENT = 10;

    /**
     * 每个消费者获取最大投递数量 默认50
     */
    private static final Integer DEFAULT_PREFETCH_COUNT = 50;

    /**
     * 配置消息消费者多线程消费
     * 使用: 在@RabbitListener(queues=queueName, containerFactory = pointTaskContainerFactory)
     */
    @Bean("pointTaskContainerFactory")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public SimpleRabbitListenerContainerFactory pointTaskContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setPrefetchCount(DEFAULT_CONCURRENT);
        factory.setConcurrentConsumers(DEFAULT_PREFETCH_COUNT);
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    /**
     * 重写消息监听
     * @param connectionFactory
     * @return
     */

    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
//        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return factory;
    }
}
