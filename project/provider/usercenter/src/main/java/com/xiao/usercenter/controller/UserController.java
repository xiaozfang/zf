package com.xiao.usercenter.controller;

import com.xiao.common.context.LoginUserContext;
import com.xiao.common.model.LoginUser;
import com.xiao.common.model.RoleBaseInfo;
import com.xiao.common.response.BaseDataResponse;
import com.xiao.common.response.BaseListResponse;
import com.xiao.dao.entity.UserInfo;
import com.xiao.usercenter.service.IRoleService;
import com.xiao.usercenter.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(tags = "用户相关接口")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @GetMapping("/detail")
    @ApiOperation("查询用户详情接口")
    public BaseDataResponse<UserInfo> getUserInfo() {
        int userid = LoginUserContext.getLoginUser().getUserid();
        return userService.getUserInfo(userid);
    }


    @GetMapping("/login")
    @ApiOperation(value = "用户登录", hidden = true)
    public LoginUser login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return userService.login(username, password);
    }

    @GetMapping("/test")
    @ApiOperation("用户测试接口")
    public BaseDataResponse<UserInfo> test() {
//        log.info(LoginUserContext.getLoginUser().getUsername());
        return userService.test();
    }

    @GetMapping("/getRoles")
    @ApiOperation("查询用户所有角色接口")
    public BaseListResponse<RoleBaseInfo> getUserRoles() {
        int userid = LoginUserContext.getLoginUser().getUserid();
        return roleService.getUserRoles(userid);
    }

}
