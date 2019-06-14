package com.xiao.domain.usercenter.response;

import lombok.Data;

import java.util.List;

@Data
public class UserLoginInfo {
    private String username;
    private List<RoleInfo> roles;
}
