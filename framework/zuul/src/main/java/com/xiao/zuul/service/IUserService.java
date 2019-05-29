package com.xiao.zuul.service;

import com.xiao.zuul.pojo.UserInfo;

public interface IUserService {
    /**
     * 验证用户登录信息
     */
    UserInfo getUserInfo(String username);
}
