package com.xiao.admin.controller;

import com.xiao.admin.service.IUserService;
import com.xiao.dao.config.datasource.DataSourceTypes;
import com.xiao.dao.config.datasource.annotation.TargetDataSource;
import com.xiao.dao.entity.User;
import com.xiao.common.response.ResponseBase;
import com.xiao.common.response.ResponseDataBase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "用户相关接口", description = "")
@RequestMapping("/api/user")
public class UserController {
    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/detail")
    @ApiOperation("用户查询详情接口")
    public ResponseDataBase<User> getUser() {
        return userService.getUser("");
    }


    @GetMapping("/test")
    @ApiOperation("用户测试接口")
    @TargetDataSource(DataSourceTypes.SECOND)
    public ResponseDataBase<User> getUserFromTest() {
        return userService.getUser("");
    }

    @PostMapping("/create")
    @ApiOperation("新增用户配置")
    public ResponseBase addUser(User user) {
        return userService.addUser(user);
    }

    @PostMapping("/edit")
    @ApiOperation("编辑用户配置")
    public ResponseBase editUser(User user) {
        return userService.editUser(user);
    }

    @GetMapping("/delete")
    @ApiOperation("删除用户配置")
    public ResponseBase deleteUser(String userid) {
        return userService.deleteUser(userid);
    }
}
