package com.xiao.admin.service;

import com.xiao.dao.entity.User;
import com.xiao.common.response.ResponseBase;
import com.xiao.common.response.ResponseDataBase;

public interface IUserService {

    ResponseDataBase<User> getUser(String userid);

    ResponseBase addUser(User user);

    ResponseBase editUser(User user);

    ResponseBase deleteUser(String userid);

}
