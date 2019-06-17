package com.xiao.usercenter.service.impl;

import com.xiao.common.response.BaseListResponse;
import com.xiao.common.response.BaseResponse;
import com.xiao.dao.entity.RoleInfo;
import com.xiao.dao.mapper.RoleInfoMapper;
import com.xiao.dao.mapper.UserRoleInfoMapper;
import com.xiao.domain.usercenter.request.RoleSearchRequest;
import com.xiao.domain.usercenter.response.RoleBaseInfo;
import com.xiao.usercenter.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    RoleInfoMapper roleInfoMapper;
    @Autowired
    UserRoleInfoMapper userRoleInfoMapper;

    @Override
    public BaseResponse create(RoleInfo roleInfo) {
        BaseResponse response = new BaseResponse();
        if (roleInfoMapper.insertSelective(roleInfo) > 0) {
            return response.success();
        } else {
            return response.fail("系统异常");
        }
    }

    @Override
    public BaseListResponse<RoleBaseInfo> list(RoleSearchRequest request) {
        return roleInfoMapper.selectRoleListByRequest(request);
    }

    @Override
    public BaseResponse addUserRoleConfig(int userid, int roleid) {
        BaseResponse response = new BaseResponse();
        if (userRoleInfoMapper.insertUserRoleInfo(userid, roleid) > 0){
            return response.success();
        } else {
            return response.fail("系统异常");
        }
    }

    @Override
    public BaseResponse changeRoleStatus(int roleid, int status) {
        BaseResponse response = new BaseResponse();
        if (roleInfoMapper.updateRoleStatus(roleid, status) > 0){
            userRoleInfoMapper.updateUserRoleStatusByRoleid(roleid, status);
            return response.success();
        } else {
            return response.fail("该角色不存在");
        }
    }

    @Override
    public BaseResponse changeUserRoleStatus(int userid, int roleid, int status) {
        BaseResponse response = new BaseResponse();
        if (userRoleInfoMapper.updateUserRoleStatus(userid, roleid, status) > 0){
            return response.success();
        } else {
            return response.fail("该用户角色不存在");
        }
    }

}
