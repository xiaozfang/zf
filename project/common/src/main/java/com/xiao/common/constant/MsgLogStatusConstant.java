package com.xiao.common.constant;

/**
 * 消息投递状态
 */
public interface MsgLogStatusConstant {
    // 消息投递中
    Integer DELIVERING = 0;
    // 投递成功
    Integer DELIVER_SUCCESS = 1;
    // 投递失败
    Integer DELIVER_FAIL = 2;
    // 已消费
    Integer CONSUMED_SUCCESS = 3;
}

