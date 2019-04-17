package com.xiao.project_1.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Order(2)
@Component
public class RegisterAspect1 {
    @Pointcut("execution(public * com.xiao.project_1.controller..*(..))")
    public void controller() {

    }

    @Pointcut("execution(public * com.xiao.project_1.service.*.*(..))")
    public void service() {

    }

    @Before("controller()")
    public void doBefore(JoinPoint point) {
        log.info("METHOD : " + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName() + "开始执行");
        log.info("ARGS : " + Arrays.toString(point.getArgs()));
    }

    @After("controller()")
    public void end(JoinPoint point){
        log.info("METHOD : " + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName() + "执行结束");
    }


    @Before("service()")
    public void doServiceBefore(JoinPoint point) {
        log.info("METHOD : " + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName() + "开始执行");
        log.info("ARGS : " + Arrays.toString(point.getArgs()));
    }
    @After("service()")
    public void doServiceEnd(JoinPoint point) {
        log.info("METHOD : " + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName() + "执行结束");
    }

}
