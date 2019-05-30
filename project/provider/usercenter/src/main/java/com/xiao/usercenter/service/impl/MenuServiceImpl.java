package com.xiao.usercenter.service.impl;

import com.xiao.domain.usercenter.request.MenuBaseInfo;
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
    private final MenuInfoMapper menuInfoMapper;

    @Autowired
    public MenuServiceImpl(MenuInfoMapper menuInfoMapper) {
        this.menuInfoMapper = menuInfoMapper;
    }

    @Override
    public ResponseBase create(MenuInfo menuInfo) {
        ResponseBase response = new ResponseBase();
        if (menuInfoMapper.insertSelective(menuInfo) > 0){
            return response.SUCCESS();
        } else {
            return response.FAIL("系统异常");
        }
    }

    @Override
    public ResponseListBase<MenuBaseInfo> list(MenuSearchRequest request) {
        ResponseListBase<MenuBaseInfo> response = new ResponseListBase<>();
        List<MenuBaseInfo> list = menuInfoMapper.list(request);
        response.setData(list);
        response.SUCCESS();
        return response;
    }
}