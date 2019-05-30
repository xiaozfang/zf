package com.xiao.common.util;

import org.apache.commons.lang3.StringUtils;

public class StringTools {
    /**
     * 判断是否为"",null," "
     */
    public static boolean isEmptyOrNull(String s) {
        return StringUtils.isEmpty(s) && StringUtils.isBlank(s) && StringUtils.isAllBlank(s) && StringUtils.isAllEmpty(s);
    }
}
