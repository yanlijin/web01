package com.yan.mapper;


import com.yan.pojo.DegreeOption;
import com.yan.pojo.Student;
import com.yan.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    //    分页查询
    public List<Student> page(StudentQueryParam studentQueryParam);

    //    保存学员信息
    @Insert("insert into student values(#{id},#{name},#{no},#{gender},#{phone},#{idCard},#{isCollege},#{address},#{degree},#{graduationDate},#{clazzId},#{violationCount},#{violationScore},#{createTime},#{updateTime})")
    public void save(Student student);

    //    根据id回显数据
    @Select("select * from student where id=#{id}")
    public Student getById(Integer id);

    //    根据id修改信息
    @Update("update student set name=#{name},no=#{no},gender=#{gender},phone=#{phone},id_card=#{idCard},is_college=#{isCollege},address=#{address},degree=#{degree},graduation_date=#{graduationDate},clazz_id=#{clazzId},violation_count=#{violationCount},violation_score=#{violationScore},create_time=#{createTime},update_time=#{updateTime} where id=#{id}")
    public void update(Student student);

    //   根据id删除信息
    public void delete(List<Integer> ids);

    //    违纪处理
    public void DA(Integer id, Short violationScore);

//    统计学历
    @MapKey("name")
    public List<Map<String,Integer>> getDegreeData();
}


