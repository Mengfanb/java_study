package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;

    /**
     * 分页查询
     */
    @GetMapping
    public Result page(EmpQueryParam empQueryParam){
        log.info("分页查询全部员工信息:{}",empQueryParam);
        PageResult<Emp> result =  empService.page(empQueryParam);
        return Result.success(result);
    }

    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工信息:{}",emp);
        empService.save(emp);
        return Result.success();

    }

    //接收前端传来的文件
    @PostMapping("/import")
    public Result imp(MultipartFile file) throws IOException {
        log.info("导入员工信息");
        //找到文件名中最后一个 . 的索引位置。将索引位置加 1，以便从 . 之后的第一个字符开始截取。
        String extendName =
                file.getOriginalFilename().
                        substring(file.getOriginalFilename().lastIndexOf(".")+1);
        String newFileName = UUID.randomUUID().toString() + "." + extendName;
        //把文件存入到某个目录下
        //服务器接受文件后会把文件存在一个临时目录下，所以需要把文件从临时目录移动到指定目录下
        file.transferTo(new File("E:/截屏/" + newFileName));

        return Result.success();
    }

    @Transactional(rollbackFor = Exception.class)
    @DeleteMapping()
    public Result delete(@RequestParam("ids") List<Integer> ids){
        log.info("删除员工信息:{}",ids);
        empService.delete(ids);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable("id") Integer id){
        log.info("根据id查询员工信息:{}",id);
        Emp emp = empService.findById(id);
        return Result.success(emp);
    }
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工信息:{}",emp);
        empService.update(emp);
        return Result.success();
    }

}
