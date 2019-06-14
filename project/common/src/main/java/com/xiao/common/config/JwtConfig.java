package com.xiao.common.config;

import java.util.UUID;

public class JwtConfig {
    public static final String JWT_ID = UUID.randomUUID().toString();

    /**
     * 加密密文
     */
    public static final String JWT_SECRET = "XIAOZHENFANG";

    /**
     * 过期时间
     */
    public static final Long JWT_TTL = 60*60*1000L;
}
