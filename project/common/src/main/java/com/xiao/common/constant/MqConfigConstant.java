package com.xiao.common.constant;

/**
 * 消息队列名称
 */
public interface MqConfigConstant {

    // 路由
    String FANOUT_EXCHANGE = "fanout_exchange";
    String TOPIC_EXCHANGE = "topic_exchange";

    String TOPIC_ROUTING_KEY_1= "topic.#.1";
    String TOPIC_ROUTING_KEY_2 = "topic.b.*";

    // 直连队列
    String HELLO_MQ = "hello";
    String TEST_MQ = "test";
    // FANOUT队列
    String FANOUT_MQ_1 = "fanout_queue_1";
    String FANOUT_MQ_2 = "fanout_queue_2";
    String FANOUT_MQ_3 = "fanout_queue_3";
    // Topic队列
    String TOPIC_MQ_1 = "topic_a_1";
    String TOPIC_MQ_2 = "topic_b_1";

}
