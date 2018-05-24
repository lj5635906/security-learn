package com.security.learn.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码保存
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-24 17:53
 **/
public interface ValidateCodeRepository {

    /**
     * 存储验证码
     *
     * @param request          ServletWebRequest
     * @param code             ValidateCode
     * @param validateCodeType ValidateCodeType
     */
    void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType);

    /**
     * 获取验证码
     *
     * @param request          ServletWebRequest
     * @param validateCodeType ValidateCodeType
     * @return ValidateCode
     */
    ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType);

    /**
     * 移除验证码
     *
     * @param request          ServletWebRequest
     * @param validateCodeType ValidateCodeType
     */
    void remove(ServletWebRequest request, ValidateCodeType validateCodeType);
}
