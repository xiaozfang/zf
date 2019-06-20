package com.xiao.usercenter.controller;

import com.xiao.common.context.LoginUserContext;
import com.xiao.common.model.LoginUser;
import com.xiao.common.response.BaseListResponse;
import com.xiao.common.response.BaseResponse;
import com.xiao.dao.entity.RoleInfo;
import com.xiao.domain.usercenter.request.RoleSearchRequest;
import com.xiao.common.model.RoleBaseInfo;
import com.xiao.usercenter.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "管理员相关接口")
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    IRoleService roleService;

    @PostMapping("/create")
    @ApiOperation("新增角色")
    public BaseResponse create(@RequestBody RoleInfo roleInfo) {
        LoginUser user = LoginUserContext.getLoginUser();
        if (!user.haveAdminPermission()){
            return new BaseResponse().fail("你没有此接口的使用权限");
        }
        return roleService.create(roleInfo);
    }

    @PostMapping("/addUserRoleConfig")
    @ApiOperation("新增用户角色配置")
    public BaseResponse addUserRoleConfig(@RequestParam int userid, @RequestParam int roleid) {
        LoginUser user = LoginUserContext.getLoginUser();
        if (!user.haveAdminPermission()){
            return new BaseResponse().fail("你没有此接口的使用权限");
        }
        return roleService.addUserRoleConfig(userid, roleid);
    }

    @GetMapping("/list")
    @ApiOperation("角色列表")
    public BaseListResponse<RoleBaseInfo> list(@RequestBody RoleSearchRequest request) {
        BaseListResponse<RoleBaseInfo> response = new BaseListResponse<>();
        LoginUser user = LoginUserContext.getLoginUser();
        if (!user.haveAdminPermission()){
            response.setCode(0);
            response.setMessage("你没有此接口的使用权限");
            return response;
        }
        return roleService.list(request);
    }

    @GetMapping("/changeRoleStatus")
    @ApiOperation("改变角色状态")
    public BaseResponse changeRoleStatus(@RequestParam int roleid, @RequestParam int status) {
        LoginUser user = LoginUserContext.getLoginUser();
        if (!user.haveAdminPermission()){
            return new BaseResponse().fail("你没有此接口的使用权限");
        }
        // todo 当作废了一个角色时，怎么实现禁用正在使用该角色的人
        //
        return roleService.changeRoleStatus(roleid, status);
    }

    @GetMapping("/changeUserRoleStatus")
    @ApiOperation("改变角色状态")
    public BaseResponse changeUserRoleStatus(@RequestParam int userid, @RequestParam int roleid, @RequestParam int status) {
        LoginUser user = LoginUserContext.getLoginUser();
        if (!user.haveAdminPermission()){
            return new BaseResponse().fail("你没有此接口的使用权限");
        }
        return roleService.changeUserRoleStatus(userid, roleid, status);
    }
}
