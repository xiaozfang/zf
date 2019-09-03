package com.xiao.dao.entity;

import lombok.Data;

/**
 * 
 *
 * @author Aha
 * @date 2019-09-02
 */

@Data
public class OrderIdInfo {
    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 0-待支付，1-已支付，2-取消订单，3-超时取消
     */
    private Integer status;
}