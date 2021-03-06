package com.xiao.database.config.aspect;

import com.xiao.common.util.StringUtils;
import com.xiao.database.config.annotation.TargetDataSource;
import com.xiao.database.config.datasource.DataSourceTypes;
import com.xiao.database.config.datasource.DatabaseContextHolder;
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

    /**
     * 前置通知(Before) 在目标方法之前执行
     * 后置通知(After) 在目标方法之后执行,此时不会关心方法的输出是什么
     * 返回通知(After-returning) 在方法成功执行后执行
     * 异常通知(After-throwing) 在方法抛出异常后执行
     * 环绕通知(Around) 在方法调用之前和调用之后执行
     */


    @Pointcut("@annotation(com.xiao.database.config.annotation.TargetDataSource)")
    public void targetDataSourcePointCut() {

    }

    @Before("targetDataSourcePointCut()")
    public void setDataSource(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        TargetDataSource tds = method.getAnnotation(TargetDataSource.class);
        if (tds == null) {
            // 设置默认数据源
            DatabaseContextHolder.setDataSourceType(DataSourceTypes.FIRST);
            log.info("set datasource to " + DataSourceTypes.FIRST);
        } else {
            String dbName = tds.value();
            if (StringUtils.isEmptyOrNull(dbName)){
                // 设置默认数据源
                DatabaseContextHolder.setDataSourceType(DataSourceTypes.FIRST);
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
