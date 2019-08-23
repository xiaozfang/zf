//package com.xiao.rabbitmq.config;
//
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessageProperties;
//import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
//import org.springframework.amqp.support.converter.MessageConversionException;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.ByteArrayInputStream;
//import java.io.ObjectInputStream;
//
//@Configuration
//public class RabbitConfig {
//    @Bean
//    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setMessageConverter(new MessageConverter() {
//            @Override
//            public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
//                return null;
//            }
//
//            @Override
//            public Object fromMessage(Message message) throws MessageConversionException {
//                try(ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(message.getBody()))){
//                    return ()ois.readObject();
//                }catch (Exception e){
//                    e.printStackTrace();
//                    return null;
//                }
//            }
//        });
//
//        return factory;
//    }
//}
