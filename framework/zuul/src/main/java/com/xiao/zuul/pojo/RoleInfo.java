package com.xiao.zuul.pojo;

import lombok.Data;

import java.util.List;

@Data
public class RoleInfo {
    /**
     * 角色id
     */
    private int roleid;

    /**
     * 角色名称
     */
    private String rolename;

    /**
     * 角色代号
     */
    private String rolecode;


    private List<MenuInfo> menus;


    public RoleInfo(int roleid, String rolename, String rolecode) {
        this.roleid = roleid;
        this.rolename = rolename;
        this.rolecode = rolecode;
    }
}
