package com.security.learn.rest;

import com.security.learn.entity.UserInfo;
import com.security.learn.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${DESCRIPTION}
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-17 14:03
 **/
@RestController
@RequestMapping("/user")
public class UserInfoRest {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("{id}")
    public UserInfo findUserById(@PathVariable Long id){
        return userInfoService.findById(id);
    }

    /**
     * 获取当前认证用户信息
     * @return
     */
    @GetMapping("/me")
    public Object getCurrentUser(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/me1")
    public Object getCurrentUser(Authentication authentication){
        return authentication;
    }

    @GetMapping("/me2")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails user){
        return user;
    }
}
