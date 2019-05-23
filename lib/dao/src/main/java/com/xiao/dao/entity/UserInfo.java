package com.xiao.dao.entity;

import java.util.Date;

import lombok.Data;

/**
 * @author Aha
 * @date 2019-05-22
 */

@Data
public class UserInfo {
    /**
     * 自增ID
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userid;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 注册邮箱
     */
    private String email;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 0-实习，1-在职，2-试用，5离职
     */
    private Integer status;

    /**
     *
     */
    private Date createtime;

    /**
     * 真实姓名
     */
    private String lastname;

    /**
     *
     */
    private Integer deleted;
}