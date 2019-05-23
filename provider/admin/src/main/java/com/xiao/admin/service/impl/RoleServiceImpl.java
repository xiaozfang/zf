package com.xiao.admin.service.impl;

import com.xiao.admin.service.IRoleService;
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
            return response.SUCCESS();
        } else {
            return response.FAIL("系统异常");
        }
    }
}
