package com.yan.service;

import com.yan.pojo.LogQueryParam;
import com.yan.pojo.OperateLog;
import com.yan.pojo.PageRuselt;

public interface LogService {
//    分页查询
    public PageRuselt<OperateLog> page(LogQueryParam logQueryParam);
}
