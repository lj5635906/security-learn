package com.security.learn.core.authentication.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * 短信验证码认证相关配置
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-18 15:24
 **/
@Component
public class SmsValidateCodeAuthenticationConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity>{

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 配置
        SmsValidateCodeAuthenticationFilter smsValidateCodeAuthenticationFilter = new SmsValidateCodeAuthenticationFilter();
        smsValidateCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsValidateCodeAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        smsValidateCodeAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

        SmsValidateCodeAuthenticationProvider smsValidateCodeAuthenticationProvider = new SmsValidateCodeAuthenticationProvider();
        smsValidateCodeAuthenticationProvider.setUserDetailsService(userDetailsService);

        http.authenticationProvider(smsValidateCodeAuthenticationProvider)
            .addFilterAfter(smsValidateCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
