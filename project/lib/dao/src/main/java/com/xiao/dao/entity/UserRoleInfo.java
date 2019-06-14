package com.xiao.dao.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Aha
 * @date 2019-05-22
 */

@Data
public class UserRoleInfo {
    /**
     *
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 角色id
     */
    private Integer roleid;

    /**
     * 0-未启用，1-启用，2-禁用
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