package com.xiao.dao.config.datasource;

public class DatabaseContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();


    public static void setDataSourceType(String type){
        contextHolder.set(type);
    }

    public static String getDataSourceType(){
        return contextHolder.get();
    }

    public static void clearDataSource() {
        contextHolder.remove();
    }
}
