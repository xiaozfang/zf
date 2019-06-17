package com.xiao.domain.usercenter.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleSearchRequest {

    @ApiModelProperty("角色ID")
    private Integer roleid;

    @ApiModelProperty("角色名称")
    private String rolename;

}
