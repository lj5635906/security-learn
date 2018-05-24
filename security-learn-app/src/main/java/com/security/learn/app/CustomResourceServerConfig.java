package com.security.learn.app;

import com.security.learn.core.authentication.mobile.SmsValidateCodeAuthenticationConfig;
import com.security.learn.core.config.ValidateCodeSecurityConfig;
import com.security.learn.core.constants.SecurityConstants;
import com.security.learn.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * 资源服务器
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-23 16:57
 **/
@Configuration
@EnableResourceServer
public class CustomResourceServerConfig extends ResourceServerConfigurerAdapter{

    @Autowired
    private SmsValidateCodeAuthenticationConfig smsValidateCodeAuthenticationConfig;
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;
    @Autowired
    private SpringSocialConfigurer springSocialConfigurer;
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private AuthenticationSuccessHandler securityAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler securityAuthenticationFailureHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                // 需要身份认证时跳转的URL
                .loginPage(SecurityConstants.DEFAULT_UNAUTHORIZED_URL)
                // 自定义登陆请求
                .loginProcessingUrl(SecurityConstants.DEFAULT__LOGIN_USERNAME_PASSWORD_URL)
                .successHandler(securityAuthenticationSuccessHandler)
                .failureHandler(securityAuthenticationFailureHandler);

        http
                // 验证码校验过滤器 配置
                .apply(validateCodeSecurityConfig)
                .and()
                // 根据短信认证 配置
                .apply(smsValidateCodeAuthenticationConfig)
                .and()
                // 添加社交登陆相关配置
                .apply(springSocialConfigurer)
                .and()
                .authorizeRequests()
                // 不需要身份认证的URL
                .antMatchers(SecurityConstants.DEFAULT_UNAUTHORIZED_URL,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                        securityProperties.getBrowser().getSignUpUrl(),
                        securityProperties.getBrowser().getSignOutUrl(),
                        "/user/register",
                        SecurityConstants.SESSION_INVALID_URL)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
        ;
    }
}
