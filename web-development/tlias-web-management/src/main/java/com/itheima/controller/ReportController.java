package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import com.itheima.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @GetMapping("/empJobData")
    public Result getEmpJobData() {
        log.info("查询员工职位分布数据");
        // 调用service查询员工职位分布数据,用Map集合集合接收
        return Result.success(reportService.getEmpJobData());

    }
}
