package com.security.learn;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * ${DESCRIPTION}
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-17 14:18
 **/
public class Test {

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String password = passwordEncoder.encode("123456");
        System.out.println(password);
    }

}
