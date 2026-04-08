package com.yan.controller;


import com.yan.pojo.Result;
import com.yan.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliyunOSSOperator  aliyunOSSOperator;
       /*本地存储方法 ---文件上传
    @PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
        log.info("文件上传:{}",file);
        //获取原始文件名
        String originalFilename = file.getOriginalFilename();
        String suffix=originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName= UUID.randomUUID().toString()+suffix;
        //保存文件
        file.transferTo(new File("D:/images/"+newFileName));
        return Result.success();

        */

    //OSS
      @PostMapping("/upload")
     public Result upload(MultipartFile file) throws Exception{
           log.info("文件上传:{}",file.getOriginalFilename());
           String url= aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
           log.info("文件路径:{}",url);
           return Result.success(url);
      }
    }

