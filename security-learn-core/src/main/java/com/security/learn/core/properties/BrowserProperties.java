package com.security.learn.core.properties;

import com.security.learn.core.config.LoginResponseType;

/**
 * ${DESCRIPTION}
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2017-11-23 14:33
 **/
public class BrowserProperties {

    private String loginPage ="/security-signIn.html";

    private LoginResponseType loginType = LoginResponseType.JSON;

    /**
     * 记住我，token过期时间默认1小时
     */
    private int rememberMeSeconds = 3600;

    public LoginResponseType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginResponseType loginType) {
        this.loginType = loginType;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }
}
