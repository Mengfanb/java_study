package com.itheima.service.impl;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {


    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;



    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());//仅仅对下一条Mapper方法起作用
        List<Emp> emplist = empMapper.list(empQueryParam);//这时候怕个helper会拦截此语句，然后改造sql语句，此时的返回值类型是page<>类型
        Page<Emp> p = (Page<Emp>) emplist;//类型一致，所以可以强转

        return new PageResult<Emp>(p.getTotal(),p.getResult());

//        //查询总数
//        Long total = empMapper.count();
//        //分页查询
//        Integer start = (page-1)*pageSize;
//        List<Emp> rows =  empMapper.list(start,pageSize);


    }
@Transactional
    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
         List<EmpExpr> exprList = emp.getExprList();
         int i =1/0;
        if(!(CollectionUtils.isEmpty(exprList))){
            exprList.forEach(expr->{
                expr.setEmpId(emp.getId());
            });
            empExprMapper.insertEmpExpr(exprList);
        }




    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
        empExprMapper.delete(ids);
    }

    @Override
    public Emp findById(Integer id) {
        return empMapper.findById(id);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
        empExprMapper.delete(Collections.singletonList(emp.getId()));
        List<EmpExpr> exprList = emp.getExprList();
        if(!(CollectionUtils.isEmpty(exprList))){
            exprList.forEach(expr->{
                expr.setEmpId(emp.getId());
            });
            empExprMapper.insertEmpExpr(exprList);
        }
    }


}
