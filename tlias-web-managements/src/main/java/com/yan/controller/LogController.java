package com.yan.controller;


import com.yan.pojo.LogQueryParam;
import com.yan.pojo.OperateLog;
import com.yan.pojo.PageRuselt;
import com.yan.pojo.Result;
import com.yan.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j

public class LogController {

    @Autowired
    private LogService logService;
//    分页查询
    @GetMapping("/log/page")
    public Result page(LogQueryParam logQueryParam){
        log.info("分页查询{}",logQueryParam);
        PageRuselt<OperateLog>  pageRuselt = logService.page(logQueryParam);
        return Result.success(pageRuselt);
    }
}
