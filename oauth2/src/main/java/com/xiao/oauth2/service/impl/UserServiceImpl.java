package com.xiao.oauth2.service.impl;


import com.xiao.oauth2.dao.UserDao;
import com.xiao.oauth2.pojo.UserInfo;
import com.xiao.oauth2.service.IUserService;
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
