package com.xiao.common.util;

public class StringTools {
    /**
     * 判断是否为"",null," "
     */
    public static boolean isEmptyOrNull(String s) {

        return s == null || s.length() == 0;
    }
}
