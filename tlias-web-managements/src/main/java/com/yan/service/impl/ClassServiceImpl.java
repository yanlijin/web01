package com.yan.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yan.mapper.ClassMapper;
import com.yan.pojo.*;
import com.yan.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassMapper classMapper;


//    根据id删除信息
    @Override
    public void delete(Integer id) {
        classMapper.delete(id);
    }

//    分页查询
    @Override
    public PageRuselt<Clazz> list(ClassQueryParam classQueryParam) {
        //1:设置分页参数(PageHelper)
        PageHelper.startPage(classQueryParam.getPage(),classQueryParam.getPageSize());
        //2:执行查询
        List<Clazz> classList =classMapper.list(classQueryParam);
        for(Clazz clazz:classList){
            LocalDateTime now = LocalDateTime.now();
            if(now.isBefore(clazz.getBeginDate().atStartOfDay())){
                clazz.setStatus("未开课");
            }else if(now.isAfter(clazz.getEndDate().atStartOfDay())){
                clazz.setStatus("已结课");
            }else {
                clazz.setStatus("授课中");
            }
        }
        //3:解析查询结果,并封装
        Page<Clazz> p =(Page<Clazz>)  classList;

        return new PageRuselt<Clazz>(p.getTotal(),p.getResult());
    }

//    增加信息
    @Override
    public void sava(Clazz clazz) {
           clazz.setCreateTime(LocalDateTime.now());
           clazz.setUpdateTime(LocalDateTime.now());
           classMapper.save(clazz);
    }

//    根据id回显信息
    @Override
    public Clazz getById(Integer id) {
        return classMapper.get(id);
    }

//    根据id修改信息
    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        classMapper.update(clazz);
    }

//    得到全部信息
    @Override
    public List<Clazz> classList() {
        return classMapper.classList();
    }


}
