package com.xiao.dao.entity;

import java.util.Date;

import lombok.Data;

/**
 * 
 *
 * @author Aha
 * @date 2019-05-22
 */

@Data
public class RoleInfo {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer roleid;

    /**
     * 
     */
    private String rolename;

    /**
     * 
     */
    private String menuids;

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