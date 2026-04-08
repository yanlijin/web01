package com.yan.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Data
public class ClassQueryParam {
    /* @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize
                        , String name, Integer gender,
     @DateTimeFormat(pattern = "yyyy-mm-dd")
     LocalDate begin,@DateTimeFormat(pattern = "yyyy-mm-dd")
     LocalDate end*/
    private Integer page=1;
    private Integer pageSize=10;
    private String name;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate begin;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate end;
}

