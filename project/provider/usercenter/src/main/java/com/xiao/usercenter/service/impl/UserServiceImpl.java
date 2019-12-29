package com.xiao.usercenter.service.impl;

import com.xiao.common.model.LoginUser;
import com.xiao.common.model.RoleBaseInfo;
import com.xiao.common.response.BaseDataResponse;
import com.xiao.common.response.BaseResponse;
import com.xiao.dao.entity.UserInfo;
import com.xiao.dao.mapper.MsgLogMapper;
import com.xiao.dao.mapper.UserInfoMapper;
import com.xiao.dao.mapper.UserRoleInfoMapper;
import com.xiao.usercenter.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserRoleInfoMapper userRoleInfoMapper;
    @Autowired
    private MsgLogMapper msgLogMapper;


    @Override
    public BaseDataResponse<UserInfo> getUserInfo(int userid) {
        BaseDataResponse<UserInfo> response = new BaseDataResponse<>();
        UserInfo user = userInfoMapper.selectUserInfoByUserId(userid);
        response.setData(user);
        response.setCode(1);
        return response;
    }

    @Override
    public BaseResponse addUser(UserInfo user) {
        userInfoMapper.insertSelective(user);
        return new BaseResponse().success("创建成功");
    }

    @Override
    public BaseResponse editUser(UserInfo user) {
        userInfoMapper.updateByPrimaryKey(user);
        return null;
    }

    @Override
    public BaseResponse deleteUser(String userid) {
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
        List<RoleBaseInfo> roles = userRoleInfoMapper.selectRolesByUserId(user.getUserid());
        loginUser.setUserid(user.getUserid());
        loginUser.setUsername(user.getLastname());
        loginUser.setRoles(roles);
        return loginUser;
    }

    @Override
    public BaseDataResponse<UserInfo> test() {
        return null;
    }

    @Override
    public BaseResponse register(String username) {
        String version1 = "1.12.2";
        int[] v1 = Arrays.stream(version1.split("\\.")).mapToInt(Integer::parseInt).toArray();
        UserInfo user = new UserInfo();
        user.setUserid(Integer.valueOf(username.replace("用户", "")));
        user.setUsername(username);
        user.setEmail(username +"@email.com");
        user.setPassword("111111");
        userInfoMapper.insertSelective(user);
        return new BaseResponse().success("创建成功");
    }

}
