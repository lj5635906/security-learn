package com.security.learn.core.constants;

/**
 * 常量
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-18 13:01
 **/
public interface SecurityConstants {

    /**
     * 默认的处理验证码的url前缀
     */
    String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";
    /**
     * 默认 当需要身份认证时 跳转的url
     */
    String DEFAULT_UNAUTHORIZED_URL = "/authentication/require";

    /**
     * 默认 的用户名密码登陆请求处理URL
     */
    String DEFAULT__LOGIN_USERNAME_PASSWORD_URL = "/authentication/form";

    /**
     * 默认 的用手机号码短信登陆请求处理URL
     */
    String DEFAULT_LOGIN_MOBILE_URL = "/authentication/mobile";

    /**
     * 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称
     */
    String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";
    /**
     * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
     */
    String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";
    /**
     * 发送短信验证码 或 验证短信验证码时，传递手机号的参数的名称
     */
    String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";
}
