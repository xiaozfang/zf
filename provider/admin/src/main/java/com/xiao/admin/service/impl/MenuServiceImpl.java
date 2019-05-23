package com.xiao.admin.service.impl;

import com.xiao.admin.service.IMenuService;
import com.xiao.common.response.ResponseBase;
import com.xiao.common.response.ResponseListBase;
import com.xiao.dao.entity.MenuInfo;
import com.xiao.dao.mapper.MenuInfoMapper;
import com.xiao.domain.admin.request.MenuSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public ResponseListBase<MenuInfo> list(MenuSearchRequest request) {
        ResponseListBase<MenuInfo> response = new ResponseListBase<>();
        List<MenuInfo> list = menuInfoMapper.list(request);
        response.setData(list);
        response.SUCCESS();
        return response;
    }
}
