package com.itheima.service.impl;

import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.ReportResult;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;
    @Override
    public ReportResult getEmpJobData() {
        List<Map<String, Object>> list = empMapper.getJobData();
        List<String> deptNames = list.stream().map(dataMap -> dataMap.get("deptName").toString()).toList();
        List<Long> empCounts = list.stream().map(dataMap -> (Long) dataMap.get("empCount")).toList();
        ReportResult jobData = new ReportResult(deptNames, empCounts);
        return jobData;
    }
}
