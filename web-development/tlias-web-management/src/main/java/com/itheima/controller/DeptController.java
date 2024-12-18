package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import com.itheima.service.impl.DeptServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
//添加一个日志对象注解
@Slf4j
//简化一下访问路径
@RequestMapping("/depts")
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;
    //@RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping
    public Result findAll(){
        List<Dept> list=deptService.findAll();
        log.info("查询全部部门信息");
        return Result.success(list);
    }
    @DeleteMapping
    //@RequestParam用于映射接收到的参数，当前端后端参数名一致时，可以省略@RequestParam
    public Result delete(@RequestParam("id") Integer id){
        deptService.deleteById(id);
        log.info("删除{}号部门",id);//{}是占位符

        return Result.success();
    }

    @PostMapping
    public Result save(@RequestBody Dept dept){

        deptService.save(dept);
        return Result.success(dept);
    }

    @GetMapping("/{id}")
    //public Result findById(@PathVariable Integer id),这样写也可以，只要上下的·..id一致就行
    public Result findById(@PathVariable("id") Integer id){
        Dept dept=deptService.findById(id);

        return Result.success(dept);
    }

    @PutMapping
    public Result update(@RequestBody Dept dept){
        deptService.upDate(dept);
        return Result.success();
    }



}

