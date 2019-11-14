package com.xiao.rabbitmq.service;

import com.rabbitmq.client.Channel;
import com.xiao.dao.mapper.MsgLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @RabbitListener 可以标注在类上面(接收所有hello队列中的消息)，需配合 @RabbitHandler 注解一起使用(根据参数进行匹配)
 * @RabbitListener 标注在类上面表示当有收到消息的时候，就交给 @RabbitHandler 的方法处理，具体使用哪个方法处理，根据 MessageConverter 转换后的参数类型
 */
@Slf4j
@Component
//@RabbitListener(queues = "test.hello")
public class MessageReceiver {
    @Autowired
    private MsgLogMapper msgLogMapper;

//    @RabbitHandler
    public void helloReceiver(Channel channel, Message message) throws IOException, TimeoutException {
        log.info(Thread.currentThread().getName() + "接收到消息：");
        try {
            log.info(Thread.currentThread().getName()+" 开始处理消息");
            Thread.sleep(5000);
//            if ("confirm".equals(msg)){
//                // 确认消息
//                channel.basicAck(message.getMessageProperties().getContentLength(), false);
//                String msgId = correlationData.getId();
//                msgLog.setMsgId(msgId);
//                msgLog.setStatus(MsgLogStatusConstant.DELIVER_SUCCESS);
//                msgLogMapper.updateByPrimaryKey(msgLog);
//                msgLogMapper.updateStatus(msgId, MsgLogStatusConstant.CONSUMED_SUCCESS);
//                channel.basicAck(tag, false);// 消费确认
//            } else if ("error".equals(msg)){
//                // 该消息被 nack 后一直重新入队列然后一直重新消费
//                channel.basicNack(message.getMessageProperties().getContentLength(), false, true);
//                channel.basicAck(message.getMessageProperties().getContentLength(), false);
//            } else if ("reject".equals(msg)){
//                //拒绝消息 消息会被丢弃，不会重回队列
//                channel.basicReject(message.getMessageProperties().getContentLength(),false);
//                channel.basicAck(message.getMessageProperties().getContentLength(), false);
//            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            channel.close();
        }
        log.info(Thread.currentThread().getName()+" 处理完毕");
    }

    @RabbitHandler
    public void helloReceiver(byte [] msg){
        log.info("msg1" + new String(msg));
    }
}
