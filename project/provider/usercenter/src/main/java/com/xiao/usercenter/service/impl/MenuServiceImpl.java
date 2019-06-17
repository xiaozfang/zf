package com.xiao.usercenter.service.impl;

import com.xiao.domain.usercenter.response.MenuBaseInfo;
import com.xiao.usercenter.service.IMenuService;
import com.xiao.common.response.BaseResponse;
import com.xiao.common.response.BaseListResponse;
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
    public BaseResponse create(MenuInfo menuInfo) {
        BaseResponse response = new BaseResponse();
        if (menuInfoMapper.insertSelective(menuInfo) > 0) {
            return response.success();
        } else {
            return response.fail("系统异常");
        }
    }

    @Override
    public BaseListResponse<MenuBaseInfo> list(MenuSearchRequest request) {
        BaseListResponse<MenuBaseInfo> response = new BaseListResponse<>();
        List<MenuBaseInfo> list = menuInfoMapper.list(request);
        response.setData(list);
        response.success();
        return response;
    }
}
