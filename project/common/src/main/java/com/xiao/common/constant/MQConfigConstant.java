package com.xiao.common.constant;

/**
 * 消息队列名称
 */
public interface MQConfigConstant {

    // 路由
    String FANOUT_EXCHANGE = "EXCHANGE_FANOUT";
    String TOPIC_EXCHANGE = "EXCHANGE_TOPIC";
    String EXCHANGE_DELAY = "EXCHANGE_DELAY";

    // ROUTING_KEY
    String TOPIC_ROUTING_KEY_1= "TOPIC.#.1";
    String TOPIC_ROUTING_KEY_2 = "TOPIC.b.*";
    String ROUTING_KEY_DELAY_15 = "ROUTING_KEY_DELAY_15";


    // 直连队列
    String HELLO_MQ = "HELLO";
    String TEST_MQ = "TEST";
    // FANOUT队列
    String FANOUT_MQ_1 = "FANOUT.MQ.1";
    String FANOUT_MQ_2 = "FANOUT.MQ.2";
    String FANOUT_MQ_3 = "FANOUT.MQ.3";
    // Topic队列
    String TOPIC_MQ_1 = "TOPIC.A.1";
    String TOPIC_MQ_2 = "TOPIC.B.2";
    // 延时消息队列
    String DELAY_MQ_15M = "DELAY_MQ_15M";

}
