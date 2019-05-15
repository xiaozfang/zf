package com.xiao.dao.config.aspect;

import com.xiao.common.util.StringTools;
import com.xiao.dao.config.datasource.DataSourceTypes;
import com.xiao.dao.config.datasource.DatabaseContextHolder;
import com.xiao.dao.config.datasource.annotation.TargetDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Order(1)
@Component
public class DataSourceAspect {

    @Pointcut("@annotation(com.xiao.dao.config.datasource.annotation.TargetDataSource)")
    public void targetDataSourcePointCut() {

    }

    @Before("targetDataSourcePointCut()")
    public void setDataSource(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        TargetDataSource tds = method.getAnnotation(TargetDataSource.class);
        if (tds == null) {
            // 设置默认数据源
            DatabaseContextHolder.setDataSourceType(DataSourceTypes.first);
            log.info("set datasource to " + DataSourceTypes.first);
        } else {
            String dbName = tds.value();
            if (StringTools.isEmptyOrNull(dbName)){
                // 设置默认数据源
                DatabaseContextHolder.setDataSourceType(DataSourceTypes.first);
            } else {
                DatabaseContextHolder.setDataSourceType(dbName);
                log.info("set datasource to " + dbName);
            }
        }
    }
    @AfterReturning("targetDataSourcePointCut()")
    public void cleanDateSource(){
        DatabaseContextHolder.clearDataSource();
        log.info("clean datasource");
    }
}
