package com.xiao.zuul.service.impl;

import com.xiao.zuul.dao.UserDao;
import com.xiao.zuul.pojo.UserInfo;
import com.xiao.zuul.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;


    @Override
    public UserInfo getUserInfo(String username) {
        return userDao.getUserByName(username);
    }
}
