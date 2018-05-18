package com.security.learn.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码处理器
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2017-11-30 15:07
 **/
public interface ValidateCodeProcessor {

    /**
     * 验证码放入session时的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 处理验证码请求
     *
     * @param request ServletWebRequest
     * @return ValidateCode
     * @throws Exception Exception
     */
    void create(ServletWebRequest request) throws Exception;

}
