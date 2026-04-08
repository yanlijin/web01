package com.yan.exception;

import com.yan.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.DuplicateFormatFlagsException;

// 移除多余的外部类，直接使用带有@RestControllerAdvice的类
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) // 明确指定处理的异常类型
    public Result handleException(Exception e) {
        log.error("程序报错", e);
        return Result.error("出错啦,请联系管理员..");
    }

    @ExceptionHandler
    public Result handleDuplicateException(DuplicateKeyException e) {
        log.error("程序出错", e);
        String msg=e.getMessage();
        int i=msg.indexOf("Duplicate entry");
        String errMsg=msg.substring(i);
        String [] arr=errMsg.split(" ");
        return  Result.error(arr[2]+"已存在");
    }
}