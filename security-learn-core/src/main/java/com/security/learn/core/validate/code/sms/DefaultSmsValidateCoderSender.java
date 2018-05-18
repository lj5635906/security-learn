package com.security.learn.core.validate.code.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 默认验证码发送实现
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-18 9:51
 **/
public class DefaultSmsValidateCoderSender implements SmsValidateCodeSender {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void send(String mobile, String code) {

        logger.info("向手机【{}】发送短信验证码【{}】", mobile, code);

    }

}
