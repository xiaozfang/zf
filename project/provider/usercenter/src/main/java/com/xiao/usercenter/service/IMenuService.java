package com.xiao.usercenter.service;

import com.xiao.common.response.BaseResponse;
import com.xiao.common.response.BaseListResponse;
import com.xiao.dao.entity.MenuInfo;
import com.xiao.domain.usercenter.response.MenuBaseInfo;
import com.xiao.domain.usercenter.request.MenuSearchRequest;

public interface IMenuService {
    BaseResponse create(MenuInfo menuInfo);

    BaseListResponse<MenuBaseInfo> list(MenuSearchRequest request);
}
