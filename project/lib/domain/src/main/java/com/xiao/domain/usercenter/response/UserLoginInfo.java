package com.xiao.domain.usercenter.response;

import com.xiao.common.model.RoleBaseInfo;
import lombok.Data;

import java.util.List;

@Data
public class UserLoginInfo {
    private String username;
    private List<RoleBaseInfo> roles;
}
