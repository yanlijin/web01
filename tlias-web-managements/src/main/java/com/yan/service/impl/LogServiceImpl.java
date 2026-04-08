package com.yan.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yan.mapper.OperateLogMapper;
import com.yan.pojo.*;
import com.yan.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public PageRuselt<OperateLog> page(LogQueryParam logQueryParam) {

            //1:设置分页参数(PageHelper)
            PageHelper.startPage(logQueryParam.getPage(),logQueryParam.getPageSize());
            //2:执行查询
            List<OperateLog> logList =operateLogMapper.list(logQueryParam);
            //3:解析查询结果,并封装
            Page<OperateLog> p =(Page<OperateLog>) logList;

            return new PageRuselt<OperateLog>(p.getTotal(),p.getResult());
    }
}
