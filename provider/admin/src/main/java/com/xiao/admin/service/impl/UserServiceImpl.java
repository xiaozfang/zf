package com.xiao.admin.service.impl;

import com.xiao.admin.service.IUserService;
import com.xiao.dao.entity.User;
import com.xiao.dao.mapper.UserMapper;
import com.xiao.common.response.ResponseBase;
import com.xiao.common.response.ResponseDataBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public ResponseDataBase<User> getUser(String userid) {
        User user = userMapper.selectUserByUserid(userid);
        log.info(user.toString());
        return null;
    }

    @Override
    public ResponseBase addUser(User user) {
        userMapper.insert(user);
        return null;
    }

    @Override
    public ResponseBase editUser(User user) {
        userMapper.Update(user);
        return null;
    }

    @Override
    public ResponseBase deleteUser(String userid) {
        User user = userMapper.deleteByUserid(userid);
        return null;
    }
}
