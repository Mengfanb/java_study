package com.itheima.exception;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result handleException(Exception e){
        log.error("服务器异常",e);
        return Result.error("服务器异常");
    }
    @ExceptionHandler
    public  Result handleException(DuplicateKeyException e){
        log.error("数据异常",e);
        String message = e.getMessage();
        //截取message"Duplicate entry"所在的字符串位置
        int index = message.lastIndexOf("Duplicate entry");
        String substring = message.substring(index);
        //把substring按空格划分为若干数组
        String[] split = substring.split(" ");
        return Result.error(split[2]+"重复输入");
    }


}
