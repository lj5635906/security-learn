package com.security.learn.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码错误异常
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-17 17:04
 **/
public class ValidateCodeException extends AuthenticationException{
    public ValidateCodeException(String msg) {
        super(msg);
    }
}
