package com.yan.controller;

import com.yan.pojo.PageRuselt;
import com.yan.pojo.Result;
import com.yan.pojo.Student;
import com.yan.pojo.StudentQueryParam;
import com.yan.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

//    分页查询
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam){
        log.info("分页查询{}",studentQueryParam);
        PageRuselt pageRuselt=studentService.page(studentQueryParam);
        return Result.success(pageRuselt);
    }

//    保存学员信息
    @PostMapping
    public Result save(@RequestBody Student student){
        log.info("保存的信息:{}",student);
        studentService.save(student);
        return Result.success();
    }

//    根据id回显数据
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("查询id为{}的学员信息",id);
        Student student=studentService.getById(id);
        return Result.success(student);
    }

//    根据id修改信息
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("修改的信息为:{}",student);
        studentService.update(student);
        return Result.success();
    }

//    根据id删除信息
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("删除id为{}的信息",ids);
        studentService.delete(ids);
        return Result.success();
    }

//    违纪处理
    @PutMapping("/violation/{id}/{score}")
    public Result DA(@PathVariable Integer id,@PathVariable("score") Short violationScore){
        log.info("违纪的同学id为:{}本次扣分:{}",id,violationScore);
        studentService.DA(id,violationScore);
        return Result.success();
    }

}
