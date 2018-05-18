package com.security.learn.core.validate.code.generator;

import com.security.learn.core.properties.SecurityProperties;
import com.security.learn.core.validate.code.ValidateCode;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 图形验证码
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2017-11-30 15:09
 **/
@org.springframework.stereotype.Component("smsCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 创建验证码图片
     * @param request HttpServletRequest
     * @return ImageCode
     */
    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
