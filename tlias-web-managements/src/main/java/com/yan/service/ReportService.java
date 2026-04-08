package com.yan.service;

import com.yan.pojo.ClassOption;
import com.yan.pojo.DegreeOption;
import com.yan.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
//    统计各部门人数
  public  JobOption getEmpJobData();

//  统计员工性别人数
    public List<Map<String, Object>> getEmpGenderData();

//    统计各班人数
    public ClassOption getClassData();

//    统计学历
    public List<Map<String,Integer>> getDegreeData();
}
