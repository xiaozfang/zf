package com.xiao.usercenter.service.impl;

import com.xiao.common.response.BaseListResponse;
import com.xiao.common.response.BaseResponse;
import com.xiao.dao.entity.RoleInfo;
import com.xiao.dao.mapper.RoleInfoMapper;
import com.xiao.dao.mapper.UserRoleInfoMapper;
import com.xiao.domain.usercenter.request.RoleSearchRequest;
import com.xiao.common.model.RoleBaseInfo;
import com.xiao.redis.RedisService;
import com.xiao.usercenter.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RedisService redisService;
    @Autowired
    private RoleInfoMapper roleInfoMapper;
    @Autowired
    private UserRoleInfoMapper userRoleInfoMapper;

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
    public BaseResponse addUserRoleConfig(int userId, int roleId) {
        BaseResponse response = new BaseResponse();
        if (userRoleInfoMapper.insertUserRoleInfo(userId, roleId) > 0) {
            return response.success();
        } else {
            return response.fail("系统异常");
        }
    }

    @Override
    public BaseResponse changeRoleStatus(int roleId, int status) {
        BaseResponse response = new BaseResponse();
        if (roleInfoMapper.updateRoleStatus(roleId, status) > 0) {
            userRoleInfoMapper.updateUserRoleStatusByRoleId(roleId, status);
            // 禁用
            if (status == 2) {
                List<Integer> delRoleList = (List<Integer>) redisService.get("del_role_list");
                if (delRoleList == null){
                    delRoleList = new ArrayList<>();
                }
                if (!delRoleList.contains(roleId)){
                    delRoleList.add(roleId);
                    redisService.set("del_role_list", delRoleList, 3600);
                }
            }
            return response.success();
        } else {
            return response.fail("该角色不存在");
        }
    }

    @Override
    public BaseResponse changeUserRoleStatus(int userId, int roleId, int status) {
        BaseResponse response = new BaseResponse();
        if (userRoleInfoMapper.updateUserRoleStatus(userId, roleId, status) > 0) {
            return response.success();
        } else {
            return response.fail("该用户角色不存在");
        }
    }

    @Override
    public BaseListResponse<RoleBaseInfo> getUserRoles(int userId) {
        BaseListResponse<RoleBaseInfo> response = new BaseListResponse<>();
        List<RoleBaseInfo> roles = userRoleInfoMapper.selectRolesByUserId(userId);
        response.setData(roles);
        response.setCode(1);
        return response;
    }

}
