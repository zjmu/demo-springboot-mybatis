package com.ucar.train.test.controller;

import com.ucar.train.test.services.UserService;
import com.ucar.train.test.util.GsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoujinmu
 * @title HelloController
 * @projectName test
 * @description TODO
 * @created 2019-08-07 09:06
 **/
@RestController
public class HelloController {

    @Autowired
    private UserService userService;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @RequestMapping("/hello1")
    public String say(){
        return driverClassName;
    }

    @RequestMapping("getUser")
    public String getAllUser(){
        return GsonUtils.toJson(userService.getAllUser());
    }
}
