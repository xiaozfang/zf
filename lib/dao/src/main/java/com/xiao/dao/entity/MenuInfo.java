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
public class MenuInfo {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer menuid;

    /**
     * 
     */
    private String menuname;

    /**
     * 
     */
    private Integer level;

    /**
     * 
     */
    private Integer parentid;

    /**
     * 
     */
    private String url;

    /**
     * 
     */
    private Integer deleted;

    /**
     * 
     */
    private Date createtime;
}