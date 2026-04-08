package com.yan.controller;


import com.yan.pojo.*;
import com.yan.service.ClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClassController {

   @Autowired
   private ClassService classService;

    @GetMapping
      public Result page(ClassQueryParam classQueryParam){
          log.info("分页查询{}",classQueryParam);
          PageRuselt pageRuselt = classService.list(classQueryParam);
          System.out.println(pageRuselt);
          return Result.success(pageRuselt);

      }
      @PostMapping
    public Result save(@RequestBody Clazz clazz){
        log.info("保存班级信息{}",clazz);
        classService.sava(clazz);
        return Result.success();

      }

      @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除ID为{}的信息",id);
        classService.delete(id);
        return Result.success();
    }

//    根据ID回显数据
    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id){
        log.info("回显ID为{}的数据",id);
       Clazz clazzList=classService.getById(id);
        return Result.success(clazzList);
    }

//    根据id修改数据
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("修改的数据:{}",clazz);
        classService.update(clazz);
        return Result.success();
    }

//    查询所有班级信息
    @GetMapping("/list")
    public Result list(){
        log.info("查询所有班级信息");
        List<Clazz> clazzList=classService.classList();
        return Result.success(clazzList);
    }


}
