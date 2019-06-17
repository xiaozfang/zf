package com.xiao.usercenter.controller;

import com.xiao.common.response.BaseListResponse;
import com.xiao.domain.usercenter.response.MenuBaseInfo;
import com.xiao.usercenter.service.IMenuService;
import com.xiao.common.response.BaseResponse;
import com.xiao.dao.entity.MenuInfo;
import com.xiao.domain.usercenter.request.MenuSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "菜单相关接口")
@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @PostMapping("/create")
    @ApiOperation("创建菜单")
    public BaseResponse create(@RequestBody MenuInfo menuInfo){
        return menuService.create(menuInfo);
    }


    @PostMapping("/list")
    @ApiOperation("查询菜单")
    public BaseListResponse<MenuBaseInfo> list(@RequestBody MenuSearchRequest request){
        return menuService.list(request);
    }
}
