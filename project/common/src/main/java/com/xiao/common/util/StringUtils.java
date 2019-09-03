package com.xiao.common.util;

public class StringUtils {
    /**
     * 判断是否为"",null," "
     */
    public static boolean isEmptyOrNull(String s) {
        return s == null || s.length() == 0 || org.apache.commons.lang3.StringUtils.isBlank(s);
    }
}
