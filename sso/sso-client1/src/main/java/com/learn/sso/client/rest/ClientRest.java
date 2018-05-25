package com.learn.sso.client.rest;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${DESCRIPTION}
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-25 16:21
 **/
@RestController
public class ClientRest {

    @GetMapping("/user")
    public Authentication user(Authentication user){
        return user;
    }


}
