package com.xiao.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息投递日志
 *
 * @author Aha
 * @date 2019-08-27
 */

@Data
public class MsgLog implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 消息唯一标识
     */
    private String msgId;

    /**
     * 交换机
     */
    private String exchange;

    /**
     * 路由键
     */
    private String routingKey;

    /**
     * 状态: 0投递中 1投递成功 2投递失败 3已消费
     */
    private Integer status;

    /**
     * 重试次数
     */
    private Integer tryCount;

    /**
     * 下一次重试时间
     */
    private Date nextTryTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 消息体, json格式化
     */
    private String msg;
}