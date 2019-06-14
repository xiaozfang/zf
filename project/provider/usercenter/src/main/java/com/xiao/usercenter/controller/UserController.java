package com.xiao.usercenter.controller;

import com.xiao.common.context.LoginUserContext;
import com.xiao.common.model.LoginUser;
import com.xiao.common.response.ResponseBase;
import com.xiao.common.response.ResponseDataBase;
import com.xiao.dao.entity.UserInfo;
import com.xiao.usercenter.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/detail")
    @ApiOperation("用户查询详情接口")
    public ResponseDataBase<UserInfo> getUser() {
        return userService.getUser("");
    }


    @GetMapping("/login")
    @ApiOperation(value = "用户登录", hidden = true)
    public LoginUser login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return userService.login(username, password);
    }

    @GetMapping("/test")
    @ApiOperation("用户测试接口")
    public ResponseDataBase<UserInfo> getUserFromTest() {
        log.info(LoginUserContext.getLoginUser().getUsername());
        return userService.getUser("");
    }

    @PostMapping("/create")
    @ApiOperation("新增用户配置")
    public ResponseBase addUser(@RequestBody UserInfo user) {
        return userService.addUser(user);
    }

    @PostMapping("/edit")
    @ApiOperation("编辑用户配置")
    public ResponseBase editUser(@RequestBody UserInfo user) {
        return userService.editUser(user);
    }

    @GetMapping("/delete")
    @ApiOperation("删除用户配置")
    public ResponseBase deleteUser(@ApiParam(name = "用户ID") String userid) {
        return userService.deleteUser(userid);
    }
}
