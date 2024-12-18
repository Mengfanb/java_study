package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpQueryParam {
    String name;
    Integer gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin;
    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end;
    Integer page=1;//接收到前端的会被覆盖，否则默认为1
    Integer pageSize=10;//同上
}
