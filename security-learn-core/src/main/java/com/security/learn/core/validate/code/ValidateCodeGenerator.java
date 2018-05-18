package com.security.learn.core.validate.code;

import com.security.learn.core.validate.code.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码生成器
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2017-11-30 15:07
 **/
public interface ValidateCodeGenerator {

    /**
     * 验证码生成器
     * @param request  ServletWebRequest
     * @return ValidateCode
     */
    ValidateCode generate(ServletWebRequest request);

}
