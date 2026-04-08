package com.yan.service;

import com.yan.pojo.ClassQueryParam;
import com.yan.pojo.Clazz;
import com.yan.pojo.EmpQueryParam;
import com.yan.pojo.PageRuselt;

import java.util.List;

public interface ClassService {
//    分页查询
    public PageRuselt<Clazz> list(ClassQueryParam ClassQueryParam);
//    保存班级信息
    public void sava(Clazz clazz);
//    根据ID删除信息
    public void delete(Integer id);
//    根据ID回显数据
    public Clazz getById(Integer id);
//    根据id修改数据
    public void update(Clazz clazz);
//    查询所有班级信息
    public List<Clazz> classList();
}
