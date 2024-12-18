package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.ReportResult;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

//分页查询
    List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * @Options(useGeneratedKeys = true,keyProperty = "id")
     * 此注解用于获取到新增数据的主键值
     */
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void insert(Emp emp);


    void delete(List<Integer> ids);

    Emp findById(Integer id);

    void update(Emp emp);

    @MapKey("deptName")
    List<Map<String,Object>>  getJobData();
}
