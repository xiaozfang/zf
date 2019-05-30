package com.xiao.dao.entity;

import java.util.Date;

import lombok.Data;

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
     *
     */
    private Integer userid;

    /**
     *
     */
    private Integer roleid;

    /**
     *
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