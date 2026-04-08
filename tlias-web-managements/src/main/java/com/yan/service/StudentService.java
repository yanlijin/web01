package com.yan.service;

import com.yan.pojo.PageRuselt;
import com.yan.pojo.Student;
import com.yan.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
//    分页查询
    public PageRuselt<Student> page(StudentQueryParam studentQueryParam);
//    保存学员信息
    public void save(Student student);
//    根据id回显数据
    public Student getById(Integer id);
//    根据id修改信息
    public void update(Student student);
//   根据id删除信息
    public void delete(List<Integer> ids);
//    违纪处理
    public void DA(Integer id,Short violationScore);
}
