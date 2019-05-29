package com.xiao.usercenter.controller;

import com.xiao.domain.usercenter.request.MenuBaseInfo;
import com.xiao.usercenter.service.IMenuService;
import com.xiao.common.response.ResponseBase;
import com.xiao.common.response.ResponseListBase;
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
    private final IMenuService menuService;

    @Autowired
    public MenuController(IMenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/create")
    @ApiOperation("创建菜单")
    public ResponseBase create(@RequestBody MenuInfo menuInfo){
        return menuService.create(menuInfo);
    }


    @PostMapping("/list")
    @ApiOperation("查询菜单")
    public ResponseListBase<MenuBaseInfo> list(@RequestBody MenuSearchRequest request){
        return menuService.list(request);
    }
}
