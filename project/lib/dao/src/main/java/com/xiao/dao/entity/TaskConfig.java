package com.xiao.dao.entity;

import lombok.Data;

import java.util.Date;

/**
 * 
 *
 * @author Aha
 * @date 2019-06-19
 */

@Data
public class TaskConfig {
    /**
     * 
     */
    private Integer id;

    /**
     * 任务编号
     */
    private Integer taskid;

    /**
     * 任务名称
     */
    private String taskname;

    /**
     * 执行时间cron表达式
     */
    private String cron;

    /**
     * 任务状态（0-不执行，1-执行）
     */
    private Integer status;

    /**
     * 
     */
    private Integer deleted;

    /**
     * 
     */
    private Date createtime;
}