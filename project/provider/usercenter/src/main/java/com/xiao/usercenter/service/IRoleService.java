package com.xiao.usercenter.service;

import com.xiao.common.response.BaseListResponse;
import com.xiao.common.response.BaseResponse;
import com.xiao.dao.entity.RoleInfo;
import com.xiao.domain.usercenter.request.RoleSearchRequest;
import com.xiao.domain.usercenter.response.RoleBaseInfo;

public interface IRoleService {
    BaseResponse create(RoleInfo roleInfo);

    BaseListResponse<RoleBaseInfo> list(RoleSearchRequest request);

    BaseResponse addUserRoleConfig(int userid, int roleid);

    BaseResponse changeRoleStatus(int roleid, int status);

    BaseResponse changeUserRoleStatus(int userid, int roleid, int status);
}
