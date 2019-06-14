package com.xiao.usercenter.service.impl;

import com.xiao.usercenter.service.IRoleService;
import com.xiao.common.response.ResponseBase;
import com.xiao.dao.entity.RoleInfo;
import com.xiao.dao.mapper.RoleInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {
    private final RoleInfoMapper roleInfoMapper;

    @Autowired
    public RoleServiceImpl(RoleInfoMapper roleInfoMapper) {
        this.roleInfoMapper = roleInfoMapper;
    }

    @Override
    public ResponseBase create(RoleInfo roleInfo) {
        ResponseBase response = new ResponseBase();
        if (roleInfoMapper.insertSelective(roleInfo) > 0){
            return response.success();
        } else {
            return response.fail("系统异常");
        }
    }
}
