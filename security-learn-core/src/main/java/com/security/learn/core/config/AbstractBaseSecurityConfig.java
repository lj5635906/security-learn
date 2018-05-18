package com.security.learn.core.config;

import com.security.learn.core.constants.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Spring Security 配置基类
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-18 17:09
 **/
public class AbstractBaseSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler securityAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler securityAuthenticationFailureHandler;

    /**
     * 配置使用密码登陆的配置
     *
     * @param http HttpSecurity
     * @throws Exception Exception
     */
    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.formLogin()
                // 需要身份认证时跳转的URL
                .loginPage(SecurityConstants.DEFAULT_UNAUTHORIZED_URL)
                // 自定义登陆请求
                .loginProcessingUrl(SecurityConstants.DEFAULT__LOGIN_USERNAME_PASSWORD_URL)
                .successHandler(securityAuthenticationSuccessHandler)
                .failureHandler(securityAuthenticationFailureHandler);
    }
}
