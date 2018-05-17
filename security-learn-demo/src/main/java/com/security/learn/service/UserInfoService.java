package com.security.learn.service;

import com.security.learn.entity.UserInfo;

/**
 * ${DESCRIPTION}
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-17 13:37
 **/
public interface UserInfoService {

    UserInfo findByUsername(String username);

    UserInfo findById(Long id);
}
