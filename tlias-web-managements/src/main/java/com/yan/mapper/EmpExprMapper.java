package com.yan.mapper;

import com.yan.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*操作员工经历*/
@Mapper
public interface EmpExprMapper {
    void insertEmpExpr(List<EmpExpr> exprList);
}
