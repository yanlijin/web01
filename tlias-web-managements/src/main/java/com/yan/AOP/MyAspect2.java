package com.yan.AOP;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class MyAspect2 {

    @Before("execution(* com.yan.service.*.*(..))")
    public void before(JoinPoint joinPoint){
        log.info("before....");
//        1.获取目标对象
        Object target = joinPoint.getTarget();
        log.info("目标对象:{}",target);

//        2.获取目标类
        String className = joinPoint.getTarget().getClass().getName();
        log.info("目标类:{}",className);

//        3.获取目标方法
        String methodName=joinPoint.getSignature().getName();
        log.info("目标方法:{}",methodName);

//        4.获取目标方法参数
        Object[] args = joinPoint.getArgs();
        log.info("目标方法参数:{}",args);
    }
}
