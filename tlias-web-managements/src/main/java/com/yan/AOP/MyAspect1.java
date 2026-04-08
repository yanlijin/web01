package com.yan.AOP;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class MyAspect1 {

    @Pointcut("execution(* com.yan.service.impl.*.*(..))")
    private void pointCut() {}

//    前置通知:目标方法运行之前运行
    @Before("pointCut()")
    public void before(){
        log.info("before....");
    }

//    环绕通知:目标方法运行前后运行
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("around....before");
        Object proceed = joinPoint.proceed();
        log.info("around....after");
        return proceed;
    }

//    后置通知-目标方法运行之后运行,无论是否出现异常都会运行
    @After("pointCut()")
    public void after(){
        log.info("after....");
    }

//    返回后通知-目标方法运行后运行,出现异常就不会运行
    @AfterReturning("pointCut()")
    public void afterReturning(){
        log.info("afterReturning....");
    }

//    异常后通知:目标方法运行之后运行,且出现异常才会运行
    @AfterThrowing("pointCut()")
    public void afterThrowing(){
        log.info("afterThrowing....");
    }
}
