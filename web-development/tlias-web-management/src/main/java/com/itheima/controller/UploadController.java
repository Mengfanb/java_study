package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliyunOssUpload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliyunOssUpload aliyunOssUpload;
    @PostMapping("/upload")
    public Result upload(MultipartFile file){
        log.info("文件上传开始{}",file.getOriginalFilename());
        try {
            String url = aliyunOssUpload.upload(file.getBytes(), file.getOriginalFilename());
            log.info("文件上传成功{}",url);
            return Result.success(url);
        } catch (Exception e) {
            log.error("文件上传失败{}",e);
            return Result.error("上传失败");
        }

    }
}
