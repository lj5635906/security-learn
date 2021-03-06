package com.security.learn.core.validate.code.sms;

import com.security.learn.core.constants.SecurityConstants;
import com.security.learn.core.validate.code.AbstractValidateCodeProcessor;
import com.security.learn.core.validate.code.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 短信验证码发送实现
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-18 12:35
 **/
@Component("smsValidateCodeProcessor")
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor {

    /**
     * 短信验证码发送器
     */
    @Autowired
    private SmsValidateCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        // 手机号码参数名
        String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
        smsCodeSender.send(mobile, validateCode.getCode());
    }

}
