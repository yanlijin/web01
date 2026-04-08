package com.yan.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yan.mapper.StudentMapper;
import com.yan.pojo.Clazz;
import com.yan.pojo.PageRuselt;
import com.yan.pojo.Student;
import com.yan.pojo.StudentQueryParam;
import com.yan.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

//    分页查询
    @Override
    public PageRuselt<Student> page(StudentQueryParam studentQueryParam) {
        //1:设置分页参数(PageHelper)
        PageHelper.startPage(studentQueryParam.getPage(),studentQueryParam.getPageSize());
        //2:执行查询
        List<Student> studentList =studentMapper.page(studentQueryParam);
        //3:解析查询结果,并封装
        Page<Student> p =(Page<Student>)  studentList;

        return new PageRuselt<Student>(p.getTotal(),p.getResult());
    }

//    保存信息
    @Override
    public void save(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.save(student);
    }

//    根据id回显数据
    @Override
    public Student getById(Integer id) {
        return studentMapper.getById(id);
    }

//    根据id修改信息

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

//    根据id删除信息

    @Override
    public void delete(List<Integer> ids) {
        studentMapper.delete(ids);
    }

//    违纪处理

    @Override
    public void DA(Integer id, Short violationScore) {
        studentMapper.DA(id,violationScore);

    }
}
