package com.yan.service;

import com.yan.pojo.Emp;
import com.yan.pojo.EmpQueryParam;
import com.yan.pojo.LoginInfo;
import com.yan.pojo.PageRuselt;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {

//    分页查询
   public PageRuselt<Emp> findAll(EmpQueryParam empQueryParam); //分页查询
   public void  sava(Emp emp);        //保存信息
   public void deleteEmp(List<Integer> ids);      //删除信息
   public Emp getInfo(Integer id);

//根据id修改信息
  public void update(Emp emp);

//  查询全部数据
  public  List<Emp> list();

//  登陆验证
    public LoginInfo login(Emp emp);
}
