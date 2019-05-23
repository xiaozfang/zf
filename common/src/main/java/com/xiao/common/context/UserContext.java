package com.xiao.common.context;

import com.xiao.common.model.CurrentUser;

public class UserContext {
    /**
     * 记录当前登录系统的人
     */
    private static ThreadLocal<CurrentUser> currentUserThreadLocal = new ThreadLocal<>();


    public static void setUser(CurrentUser user) {
        currentUserThreadLocal.set(user);
    }

    public static CurrentUser getUser() {
        return currentUserThreadLocal.get();
    }

    public static void removeUser() {
        currentUserThreadLocal.remove();
    }
}
