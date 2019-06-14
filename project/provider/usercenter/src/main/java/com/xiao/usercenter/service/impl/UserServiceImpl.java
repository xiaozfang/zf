package com.xiao.usercenter.service.impl;

import com.xiao.common.model.LoginUser;
import com.xiao.common.response.ResponseBase;
import com.xiao.common.response.ResponseDataBase;
import com.xiao.dao.entity.UserInfo;
import com.xiao.dao.mapper.UserInfoMapper;
import com.xiao.dao.mapper.UserRoleInfoMapper;
import com.xiao.database.config.annotation.TargetDataSource;
import com.xiao.usercenter.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserRoleInfoMapper userRoleInfoMapper;


    @Override
    @TargetDataSource("second")
    public ResponseDataBase<UserInfo> getUser(String userid) {

        log.info(userInfoMapper.test(userid));
        return null;
    }

    @Override
    public ResponseBase addUser(UserInfo user) {
        userInfoMapper.insertSelective(user);
        return null;
    }

    @Override
    public ResponseBase editUser(UserInfo user) {
        userInfoMapper.updateByPrimaryKey(user);
        return null;
    }

    @Override
    public ResponseBase deleteUser(String userid) {
//        User user = userInfoMapper.deleteByPrimaryKey(userid);
        return null;
    }

    @Override
    public LoginUser login(String username, String password) {
        log.info("登录成功");
        LoginUser loginUser = new LoginUser();
        UserInfo user = userInfoMapper.login(username, password);
        if (user == null) {
            return null;
        }
        loginUser.setUserid(user.getUserid());
        loginUser.setUsername(user.getLastname());
        loginUser.setRoles(userRoleInfoMapper.selectRoleidsByUserid(user.getUserid()));
        return loginUser;
    }
}
