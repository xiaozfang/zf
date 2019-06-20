package com.xiao.zuul.service;

import com.xiao.common.model.LoginFrom;
import com.xiao.common.model.LoginUser;

public interface IUserService {
    /**
     * 验证用户登录信息
     */
    LoginUser getUserInfo(LoginFrom loginFrom);
}
