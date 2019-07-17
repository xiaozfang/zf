package com.xiao.usercenter.service;

import com.xiao.common.model.LoginUser;
import com.xiao.common.response.BaseResponse;
import com.xiao.common.response.BaseDataResponse;
import com.xiao.dao.entity.UserInfo;

public interface IUserService {

    BaseDataResponse<UserInfo> getUserInfo(int userid);

    BaseResponse addUser(UserInfo user);

    BaseResponse editUser(UserInfo user);

    BaseResponse deleteUser(String userid);


    LoginUser login(String username, String password);

    BaseDataResponse<UserInfo> test();
}
