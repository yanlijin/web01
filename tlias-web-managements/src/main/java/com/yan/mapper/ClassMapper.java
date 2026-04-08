package com.yan.mapper;


import com.yan.pojo.ClassQueryParam;
import com.yan.pojo.Clazz;
import com.yan.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClassMapper {
//    分页查询班级信息
    public List<Clazz> list(ClassQueryParam classQueryParam);
//    保存信息
    @Insert("insert into clazz values(#{id},#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    public void save(Clazz clazz);

//    根据id删除信息
    @Delete("delete from clazz where id=#{id}")
    public void delete(Integer id);

//    根据id回显数据
    public Clazz get(Integer id);
//    根据id修改数据
    @Update("update clazz set name=#{name},room=#{room},begin_date=#{beginDate},end_date=#{endDate},master_id=#{masterId},subject=#{subject} where id=#{id}")
    public void update(Clazz clazz);

//    查询所有班级
    @Select("select * from clazz")
    public List<Clazz> classList();

//    统计各班人数
    @MapKey("pos")
    public List<Map<String,Object>> countClassData();
}
