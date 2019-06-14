package com.xiao.usercenter.controller;

import com.xiao.usercenter.service.IRoleService;
import com.xiao.common.response.ResponseBase;
import com.xiao.dao.entity.RoleInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "角色相关接口")
@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    IRoleService roleService;

    @PostMapping("/create")
    @ApiOperation("新增角色")
    public ResponseBase create(@RequestBody RoleInfo roleInfo) {
        return roleService.create(roleInfo);
    }
}
