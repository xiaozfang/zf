package com.xiao.common.model;

import lombok.Data;

import java.util.List;

@Data
public class LoginUser {
    private int userid;
    private String username;

    private List<Integer> roles;
    private int currentrole;

    public boolean checkRole(){
        return roles.contains(currentrole);
    }

    public boolean haveAdminPermission(){
        return currentrole == 100;
    }
}
