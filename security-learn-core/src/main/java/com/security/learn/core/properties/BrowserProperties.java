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
}
