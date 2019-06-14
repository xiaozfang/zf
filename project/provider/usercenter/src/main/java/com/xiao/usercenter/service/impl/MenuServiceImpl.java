package com.xiao.usercenter.service.impl;

import com.xiao.domain.usercenter.response.MenuBaseInfo;
import com.xiao.usercenter.service.IMenuService;
import com.xiao.common.response.ResponseBase;
import com.xiao.common.response.ResponseListBase;
import com.xiao.dao.entity.MenuInfo;
import com.xiao.dao.mapper.MenuInfoMapper;
import com.xiao.domain.usercenter.request.MenuSearchRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private MenuInfoMapper menuInfoMapper;

    @Override
    public ResponseBase create(MenuInfo menuInfo) {
        ResponseBase response = new ResponseBase();
        if (menuInfoMapper.insertSelective(menuInfo) > 0) {
            return response.success();
        } else {
            return response.fail("系统异常");
        }
    }

    @Override
    public ResponseListBase<MenuBaseInfo> list(MenuSearchRequest request) {
        ResponseListBase<MenuBaseInfo> response = new ResponseListBase<>();
        List<MenuBaseInfo> list = menuInfoMapper.list(request);
        response.setData(list);
        response.success();
        return response;
    }
}
