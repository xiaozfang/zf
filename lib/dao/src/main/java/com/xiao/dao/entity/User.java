package com.xiao.dao.entity;

import lombok.Data;

/**
 * 
 *
 * @author Aha
 * @date   2019-05-15
 */

@Data
public class User {
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
    private String realname;

    /**
     * 注册邮箱
     */
    private String email;

    /**
     * 登录密码
     */
    private String password;
}