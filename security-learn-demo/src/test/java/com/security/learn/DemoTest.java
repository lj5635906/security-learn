package com.security.learn;

import com.security.learn.entity.UserInfo;
import com.security.learn.service.UserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试用例
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2017-11-01 10:39
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecurityDemoApplication.class)
public class DemoTest {

    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void test(){
        UserInfo userInfo = userInfoService.findByUsername("roger");
        System.out.println(userInfo);
    }

}
