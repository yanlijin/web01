package com.yan.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yan.mapper.EmpExprMapper;
import com.yan.mapper.EmpMapper;
import com.yan.pojo.*;
import com.yan.service.EmpService;
import com.yan.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;


    //pagehelper
    @Override
    public PageRuselt<Emp> findAll(EmpQueryParam empQueryParam) {
        //1:设置分页参数(PageHelper)
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        //2:执行查询
        List<Emp> empList =empMapper.list(empQueryParam);
        //3:解析查询结果,并封装
        Page<Emp> p =(Page<Emp>)  empList;
        return new PageRuselt<Emp>(p.getTotal(),p.getResult());
    }

    @Transactional
    @Override
    public void sava(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
         empMapper.insertEmp(emp);

         List<EmpExpr> exprList=emp.getExprList();
         if(!CollectionUtils.isEmpty(exprList)){
             //遍历集合,为EmpID赋值
             exprList.forEach(empExpr -> {
                 empExpr.setEmpId(emp.getId());
             });
             empExprMapper.insertEmpExpr(exprList);
         }
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteEmp(List<Integer> ids) {
        empMapper.deleteEmp(ids);     //删除员工基本信息表

        empMapper.deleteEmpExpr(ids); //删除员工工作经历表
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getInfo(id);
    }

    //修改员工信息
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        //1:根据ID修改信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        //2:根据ID修改员工经历信息
           //2.1:先删除
        empMapper.deleteEmpExpr(Arrays.asList(emp.getId()));
           //2.2添加
        List<EmpExpr> exprList=emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> {empExpr.setEmpId(emp.getId());});
            empExprMapper.insertEmpExpr(exprList);
        }

    }

//    查询全部信息
    @Override
    public List<Emp> list() {
        return empMapper.listEmp();
    }

//    登录验证
    public LoginInfo login(Emp emp){
//        1.调用mapper接口,根据用户名和密码查询员工信息
        Emp e=empMapper.selectByUsernameAndPassword(emp);

//        2.判断是否存在这个员工,如果存在则组装信息
        if(e!=null){
            log.info("登录成功,员工信息:{}",e);

//            生成JWT令牌
            Map<String,Object> claims=new HashMap<>();
            claims.put("id",e.getId());
            claims.put("name",e.getUsername());
            String jwt= JwtUtils.generateToken(claims);


            return new LoginInfo(e.getId(),e.getUsername(),e.getName(),jwt);
        }
        return null;
    }










/*原始分页查询
    @Override
    public PageRuselt<Emp> findAll(Integer page, Integer pageSize) {
        Long total=empMapper.getTotal();
        Integer start=(page-1)*pageSize;
        List<Emp> rows=empMapper.list(start,pageSize);
        return new PageRuselt<Emp>(total,rows);
    }
    */
 //pagehelp

}
