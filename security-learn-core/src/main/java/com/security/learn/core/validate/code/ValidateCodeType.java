package com.security.learn.core.validate.code;

import com.security.learn.core.constants.SecurityConstants;

/**
 * 验证码类型
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-18 12:45
 **/
public enum ValidateCodeType {

    SMS{
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    },
    IMAGE{
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    };

    /**
     * 校验是从请求中获取的参数的名字
     * @return String
     */
    public abstract String getParamNameOnValidate();

}
