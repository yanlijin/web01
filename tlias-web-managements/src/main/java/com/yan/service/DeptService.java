package com.yan.service;

import com.yan.pojo.Dept;

import java.util.List;

public interface DeptService {
   public List<Dept> getDepts();
   public void DeleteDept(Integer id);
   public void insertDept(Dept dept);
   public Dept getDept(Integer id);
   public void updateDept(Dept dept);
}
