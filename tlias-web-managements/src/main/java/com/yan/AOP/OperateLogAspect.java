package com.yan.AOP;
import com.yan.mapper.OperateLogMapper;
import com.yan.pojo.OperateLog;
import com.yan.utils.CurrentHolder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperateLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;


    @Around("@annotation(com.yan.anno.Log)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 记录开始时间
        long startTime = System.currentTimeMillis();
        
        // 执行目标方法
        Object result = joinPoint.proceed();
        
        // 记录结束时间
        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;
        
        // 获取方法签名信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        // 构建操作日志对象
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(CurrentHolder.getCurrentId());
        operateLog.setOperateTime(LocalDateTime.now());
        operateLog.setClassName(joinPoint.getTarget().getClass().getName());
        operateLog.setMethodName(joinPoint.getSignature().getName());
        operateLog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        operateLog.setReturnValue(result != null ? result.toString() : "void");
        operateLog.setCostTime(costTime);
        

        log.info("保存操作日志:{}", operateLog);
        // 保存操作日志
        operateLogMapper.insert(operateLog);
        
        return result;
    }
}