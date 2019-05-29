package com.xiao.usercenter.service;

import com.xiao.common.response.ResponseBase;
import com.xiao.dao.entity.RoleInfo;

public interface IRoleService {
    ResponseBase create(RoleInfo roleInfo);
}
