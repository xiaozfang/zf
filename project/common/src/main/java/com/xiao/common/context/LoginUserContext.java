package com.xiao.common.context;

import com.xiao.common.model.LoginUser;

public class LoginUserContext {
    /**
     * 记录当前登录系统的人
     */
    private static ThreadLocal<LoginUser> loginUserThreadLocal = new ThreadLocal<>();


    public static void setLoginUser(LoginUser user) {
        loginUserThreadLocal.set(user);
    }

    public static LoginUser getLoginUser() {
        return loginUserThreadLocal.get();
    }

    public static void removeLoginUser() {
        loginUserThreadLocal.remove();
    }
}
