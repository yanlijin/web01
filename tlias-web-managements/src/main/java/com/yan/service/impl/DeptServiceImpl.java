package com.yan.service.impl;

import com.yan.mapper.DeptMapper;
import com.yan.pojo.Dept;
import com.yan.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

//    获取部门数据
    @Override
    public List<Dept> getDepts() {
         return deptMapper.getDepts();
    }

//    根据id删除数据
    @Override
    public void DeleteDept(Integer id) {



        deptMapper.deleteDept(id);
    }

//    插入数据
    @Override
    public void insertDept(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insertDept(dept);
    }

//    根据id回显数据
    @Override
    public Dept getDept(Integer id) {
      return   deptMapper.getDept(id);
    }

//    根据id修改数据
    @Override
    public void  updateDept(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updateDept(dept);

    }

}
