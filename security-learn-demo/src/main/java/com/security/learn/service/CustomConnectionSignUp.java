package com.security.learn.service;

import com.security.learn.entity.UserInfo;
import com.security.learn.reposityory.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 社交登陆自动注册,如果没有实现ConnectionSignUp，就跳转到注册页面
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-22 14:25
 **/
@Component
public class CustomConnectionSignUp implements ConnectionSignUp {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public String execute(Connection<?> connection) {
        // 根据社交用户信息进行注册，并返回用户标识
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(connection.getDisplayName());
        userInfo.setCreateTime(new Date());
        userInfo.setUpdateTime(userInfo.getCreateTime());
        userInfo.setDeleteFlag(0);
        userInfoRepository.save(userInfo);
        if (null == userInfo.getUserId()){
            logger.error("第三方登陆自动注册用户出现异常");
            throw new RuntimeException("第三方登陆自动注册用户出现异常");
        }
        return String.valueOf(userInfo.getUserId());
    }

}
