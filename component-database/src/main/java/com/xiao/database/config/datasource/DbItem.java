package com.xiao.database.config.datasource;

import lombok.Data;

@Data
public class DbItem {
    private String url;
    private String driverName;
    private String username;
    private String password;
}
