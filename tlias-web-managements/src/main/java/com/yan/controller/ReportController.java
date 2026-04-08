package com.yan.controller;


import com.yan.pojo.ClassOption;
import com.yan.pojo.DegreeOption;
import com.yan.pojo.JobOption;
import com.yan.pojo.Result;
import com.yan.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;

//    统计各部门人数
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("统计各部门人数");
        JobOption jobOption =reportService.getEmpJobData();
        return Result.success(jobOption);
    }

//    统计员工性别人数
    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("统计员工性别人数");
        List<Map<String,Object>> genderList =reportService.getEmpGenderData();
        return Result.success(genderList);
    }

//    统计各班人数
    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
        log.info("统计各班人数");
        ClassOption classOption=reportService.getClassData();
        return Result.success(classOption);
    }

//    统计学员学历
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("统计学历");
        List<Map<String,Integer>> list =reportService.getDegreeData();
        return Result.success(list);
    }

}
