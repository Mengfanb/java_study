package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportResult {
    //部门名称集合
    private List<String> deptNames;
    //部门员工数量集合
    private List<Long> empCounts;
}
