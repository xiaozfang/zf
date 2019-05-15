package com.xiao.admin.controller;

import com.xiao.admin.service.IUserService;
import com.xiao.dao.config.datasource.DataSourceTypes;
import com.xiao.dao.config.datasource.annotation.TargetDataSource;
import com.xiao.dao.entity.User;
import com.xiao.domain.common.ResponseBase;
import com.xiao.domain.common.ResponseDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/detail")
    public ResponseDataBase<User> getUser() {
        return userService.getUser("");
    }


    @RequestMapping("/test")
    @TargetDataSource(DataSourceTypes.SECOND)
    public ResponseDataBase<User> getUserFromTest() {
        return userService.getUser("");
    }

    @PostMapping("/create")
    public ResponseBase addUser(User user) {
        return userService.addUser(user);
    }

    @PostMapping("/edit")
    public ResponseBase editUser(User user) {
        return userService.editUser(user);
    }

    @GetMapping("/delete")
    public ResponseBase deleteUser(String userid) {
        return userService.deleteUser(userid);
    }
}
