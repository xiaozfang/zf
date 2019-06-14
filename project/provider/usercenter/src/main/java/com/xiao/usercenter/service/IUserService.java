package com.xiao.usercenter.service;

import com.xiao.common.response.ResponseBase;
import com.xiao.common.response.ResponseDataBase;
import com.xiao.dao.entity.UserInfo;

public interface IUserService {

    ResponseDataBase<UserInfo> getUser(String userid);

    ResponseBase addUser(UserInfo user);

    ResponseBase editUser(UserInfo user);

    ResponseBase deleteUser(String userid);



}
