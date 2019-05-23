package com.xiao.admin.service;

import com.xiao.common.response.ResponseBase;
import com.xiao.common.response.ResponseListBase;
import com.xiao.dao.entity.MenuInfo;
import com.xiao.domain.admin.request.MenuSearchRequest;

public interface IMenuService {
    ResponseBase create(MenuInfo menuInfo);

    ResponseListBase<MenuInfo> list(MenuSearchRequest request);
}
