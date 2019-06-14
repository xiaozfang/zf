package com.xiao.zuul.service;

import com.xiao.zuul.domain.LoginFrom;
import com.xiao.zuul.pojo.LoginUser;

public interface IUserService {
    /**
     * 验证用户登录信息
     */
    LoginUser getUserInfo(LoginFrom loginFrom);
}
