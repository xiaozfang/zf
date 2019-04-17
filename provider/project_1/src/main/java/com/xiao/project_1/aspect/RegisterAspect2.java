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
@Order(1)
@Component
public class RegisterAspect2 {
    @Pointcut("execution(public * com.xiao.project_1.controller..*(..))")
    public void controller() {

    }

    @Before("controller()")
    public void doBefore(JoinPoint point) {
        log.info("方法 : " + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName() + "开始执行");
        log.info("参数 : " + Arrays.toString(point.getArgs()));
    }

    @After("controller()")
    public void end(JoinPoint point){
        log.info("方法 : " + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName() + "执行结束");
    }

}
