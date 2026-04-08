package com.yan.controller;


import com.yan.mapper.EmpMapper;
import com.yan.pojo.Emp;
import com.yan.pojo.EmpQueryParam;
import com.yan.pojo.PageRuselt;
import com.yan.pojo.Result;
import com.yan.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {
   @Autowired
   private EmpService empService;


    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("分页查询:{}",empQueryParam);

        PageRuselt pageRuselt =empService.findAll(empQueryParam);
        System.out.println(pageRuselt);
        return Result.success(pageRuselt);

    }

    //保存信息
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("新增的信息:{}",emp);
        empService.sava(emp);


        return Result.success();
    }
    //删除信息
    @DeleteMapping
    public Result deleteEmp(@RequestParam List<Integer> ids) {
        log.info("删除id为{}的信息",ids);
        empService.deleteEmp(ids);
        return Result.success();
    }
    //根据id获取信息
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("获取id为{}的信息",id);
        Emp emp=empService.getInfo(id);
        return Result.success(emp);
    }
    //修改员工信息
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改id为{}的员工信息",emp.getId());
        empService.update(emp);
        return Result.success();
    }
    //获取全部信息
    @GetMapping("/list")
    public Result list(){
        log.info("获取全部员工信息");
        List<Emp> empList=empService.list();
        return Result.success(empList);
    }
}
