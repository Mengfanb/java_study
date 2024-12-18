package com.itheima.mapper;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select * from dept order by update_time desc ")
    public List<Dept> findAll();

    @Delete("delete from dept where id = #{id}")
    public void deleteById(Integer id);

    @Insert("insert into dept(name,create_time,update_time)" +
            // 切记values里面写的是java属性值，不是MySQL列名
            " values (#{name},#{createTime},#{updateTime})")
    void save(Dept dept);

    @Select("select id,name,id, name, create_time, update_time from dept where id = #{id}")
    Dept findById(Integer id);

    //修改数据库的值
    @Update("update dept set name = #{name},update_time = #{updateTime} where id = #{id}")
    void upDate(Dept dept);
}
