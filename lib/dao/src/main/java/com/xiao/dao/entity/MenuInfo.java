package com.xiao.dao.entity;

import lombok.Data;

import java.util.Date;

/**
 * 
 *
 * @author Aha
 * @date 2019-05-22
 */

@Data
public class MenuInfo {
    /**
     * 菜单id
     */
    private Integer id;

    /**
     * 菜单名字
     */
    private String menuname;

    /**
     * 菜单等级
     */
    private Integer level;

    /**
     * 父菜单id
     */
    private Integer parentid;

    /**
     * 跳转url
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