package com.itheima.service;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;

import java.util.List;

public interface DeptService {
    public List<Dept> findAll();

    public void deleteById(Integer id);

    void save(Dept dept);

    Dept findById(Integer id);

    void upDate(Dept dept);
}
