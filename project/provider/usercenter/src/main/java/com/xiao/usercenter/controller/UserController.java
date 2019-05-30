package com.xiao.usercenter.controller;

import com.xiao.usercenter.service.IUserService;
import com.xiao.common.response.ResponseBase;
import com.xiao.common.response.ResponseDataBase;
import com.xiao.dao.config.datasource.DataSourceTypes;
import com.xiao.dao.config.datasource.annotation.TargetDataSource;
import com.xiao.dao.entity.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户相关接口")
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/detail")
    @ApiOperation("用户查询详情接口")
    public ResponseDataBase<UserInfo> getUser(@ApiParam(name = "用户ID") String userid) {
        return userService.getUser("");
    }


    @GetMapping("/test")
    @ApiOperation("用户测试接口")
    @TargetDataSource(DataSourceTypes.SECOND)
    public ResponseDataBase<UserInfo> getUserFromTest() {
        return null;
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
