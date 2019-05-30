package com.xiao.usercenter.service;

import com.xiao.common.response.ResponseBase;
import com.xiao.common.response.ResponseListBase;
import com.xiao.dao.entity.MenuInfo;
import com.xiao.domain.usercenter.request.MenuBaseInfo;
import com.xiao.domain.usercenter.request.MenuSearchRequest;

public interface IMenuService {
    ResponseBase create(MenuInfo menuInfo);

    ResponseListBase<MenuBaseInfo> list(MenuSearchRequest request);
}
