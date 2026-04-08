package com.yan.controller;


import com.yan.anno.Log;
import com.yan.pojo.Dept;
import com.yan.pojo.Result;
import com.yan.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

   // private static final Logger LOG= LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    //@RequestMapping(value = "/depts" ,method = RequestMethod.GET) //指定请求方式
    @GetMapping
    public Result list(){
        //System.out.println("查询全部部门信息");
        log.info("查询全部部门信息");
        List<Dept> deptList=deptService.getDepts();
        return Result.success(deptList);
    }

    @Log
    @DeleteMapping
        public Result deleteDept(Integer id){
       // System.out.println("删除id为"+id+"的部门");
        log.info("根据ID删除部门:{}",id);
        deptService.DeleteDept(id);
            return Result.success();
        }

        @Log
    @PostMapping
    public Result insertDept(@RequestBody Dept dept){
       // System.out.println("新增部门:"+dept.getName());
        log.info("新增部门:{}",dept);
        deptService.insertDept(dept);
        return Result.success();
    }
    @GetMapping("/{id}") //根据id查询部门
    public Result getDept(@PathVariable Integer id){
       // System.out.println("查询id为"+id+"的部门");
        log.info("根据ID查询部门:{}",id);
        Dept dept=deptService.getDept(id);
        return Result.success(dept);
    }

    @Log
    @PutMapping
    public Result updateDept(@RequestBody Dept dept){
       // System.out.println("修改数据");
        log.info("修改部门:{}",dept);
        deptService.updateDept(dept);
        return Result.success();
    }
    }

