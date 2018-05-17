package com.security.learn.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${DESCRIPTION}
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-17 11:15
 **/
@RestController
@RequestMapping("/hello")
public class HelloRest {

    @GetMapping
    public String sayHello(){
        return "hello security.";
    }
}
