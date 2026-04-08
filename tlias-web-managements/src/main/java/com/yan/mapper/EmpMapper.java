package com.yan.mapper;
/*操作员工信息*/


import com.yan.pojo.Emp;
import com.yan.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface EmpMapper {
     /*//查询总记录数
    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
    public Long getTotal();

    //分页查询
    @Select("select e.*,d.name deptName from emp e left join dept d on " +
            "e.dept_id = d.id order by update_time desc limit #{start},#{pageSize}")
    public List<Emp> list(Integer start,Integer pageSize);*/
    //---------------------原始分页查询

    //pagehelper
    // @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id order by update_time desc")
    public List<Emp> list(EmpQueryParam empQueryParam);

    @Options(useGeneratedKeys = true, keyProperty = "id")//获取到生成的主键 --主键返回
    @Insert("insert into emp(username,name,gender,phone,job,salary,image,entry_date,dept_id,create_time,update_time)" +
            "values(#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insertEmp(Emp emp);


    void deleteEmp(List<Integer> ids);     //删除员工信息

    void deleteEmpExpr(List<Integer> ids);      //删除员工经历


    Emp getInfo(Integer id); //根据id获取信息

    void updateById(Emp emp);   //根据id修改数据

    //    统计各班人数
    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    //    统计性别人数
    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();

    //    获取全部员工信息
    @Select("select * from emp")
    public List<Emp> listEmp();


//登录验证
    @Select("select * from emp where username=#{username} and password=#{password}")
    public Emp selectByUsernameAndPassword(Emp emp);
}


