package com.yan.mapper;

import com.yan.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select * from dept order by update_time desc")
    public List<Dept> getDepts();

    @Delete("delete from dept where id=#{id}")
    public void deleteDept(Integer id);

    @Insert("insert into dept values(#{id},#{name},#{createTime},#{updateTime})")
    public void insertDept(Dept dept);

    @Select("select * from dept where id=#{id}")
    public Dept getDept(Integer id);

    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    public void updateDept(Dept dept);
}
