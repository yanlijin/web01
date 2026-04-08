package com.yan.service.impl;

import com.yan.mapper.ClassMapper;
import com.yan.mapper.EmpMapper;
import com.yan.mapper.StudentMapper;
import com.yan.pojo.ClassOption;
import com.yan.pojo.DegreeOption;
import com.yan.pojo.JobOption;
import com.yan.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ClassMapper classMapper;

//    统计性别人数
    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public JobOption getEmpJobData() {
//      统计各部门人数
//        1.调用Mapper接口,获取统计数据
        List<Map<String, Object>> list=empMapper.countEmpJobData();
//        2.组装结果
        List<Object> jobList = list.stream().map(dataMap->dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap->dataMap.get("num")).toList();

        return new JobOption(jobList,dataList);
    }

//    统计各班人数

    @Override
    public ClassOption getClassData() {
        //        1.调用Mapper接口,获取统计数据
        List<Map<String,Object>> list=classMapper.countClassData();
        //        2.组装结果
        List<Object> clazzList = list.stream().map(dataMap->dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap->dataMap.get("num")).toList();

        return new ClassOption(clazzList,dataList);

    }
//    统计学历

    @Override
    public List<Map<String,Integer>> getDegreeData() {
        return studentMapper.getDegreeData();
    }
}
