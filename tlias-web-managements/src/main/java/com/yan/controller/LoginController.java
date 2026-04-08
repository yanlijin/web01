package com.yan.controller;

import com.yan.pojo.Emp;
import com.yan.pojo.LoginInfo;
import com.yan.pojo.Result;
import com.yan.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {
     @Autowired
     private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("登录:{},{}",emp.getUsername(),emp.getPassword());
        LoginInfo loginInfo=empService.login(emp);
        if(loginInfo!=null){
            return Result.success(loginInfo);
        }else{
            return Result.error("用户名或密码错误...");
        }
    }
}
