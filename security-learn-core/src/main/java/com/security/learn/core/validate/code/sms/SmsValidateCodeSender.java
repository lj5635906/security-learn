package com.security.learn.core.validate.code.sms;

/**
 * 发送短信验证码接口
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-18 9:49
 **/
public interface SmsValidateCodeSender {

    /**
     * 发送短信接口
     * @param mobile 手机号
     * @param code 发送内容
     */
    void send(String mobile, String code);

}
