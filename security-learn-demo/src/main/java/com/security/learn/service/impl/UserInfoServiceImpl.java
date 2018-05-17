package com.security.learn.service.impl;

import com.security.learn.entity.UserInfo;
import com.security.learn.reposityory.UserInfoRepository;
import com.security.learn.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ${DESCRIPTION}
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-17 13:38
 **/
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository repository;

    @Override
    public UserInfo findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public UserInfo findById(Long id) {
        return repository.findOne(id);
    }
}
