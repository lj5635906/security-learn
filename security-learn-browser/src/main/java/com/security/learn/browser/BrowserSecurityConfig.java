package com.security.learn.browser;

import com.security.learn.browser.logout.CustomLogoutSuccessHandler;
import com.security.learn.browser.session.CustomExpiredSessionStrategy;
import com.security.learn.core.authentication.mobile.SmsValidateCodeAuthenticationConfig;
import com.security.learn.core.config.AbstractBaseSecurityConfig;
import com.security.learn.core.config.ValidateCodeSecurityConfig;
import com.security.learn.core.constants.SecurityConstants;
import com.security.learn.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * ${DESCRIPTION}
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-17 11:29
 **/
@Configuration
public class BrowserSecurityConfig extends AbstractBaseSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private SmsValidateCodeAuthenticationConfig smsValidateCodeAuthenticationConfig;
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;
    @Autowired
    private SpringSocialConfigurer springSocialConfigurer;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        applyPasswordAuthenticationConfig(http);

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
                // 记住我相关
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                    .userDetailsService(userDetailsService)
                    .and()
                .sessionManagement()
                    // session 过期后跳转地址
                    .invalidSessionUrl(SecurityConstants.SESSION_INVALID_URL)
                    // 用户并发登陆
                    .maximumSessions(1)
                    // 用户登陆数大道最大并发数，限制后面用户登陆
                    .maxSessionsPreventsLogin(true)
                    .expiredSessionStrategy(new CustomExpiredSessionStrategy())
                    .and()
                    .and()
                .logout()
                    .logoutUrl("/logout")
                    // 退出操作 logoutSuccessHandler和logoutSuccessUrl互斥，只能使用一个
                    .logoutSuccessHandler(logoutSuccessHandler())
                    // 删除Cookie
                    .deleteCookies("JESSIONID")
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

    /**
     * rememberMme 持久化 token
     *
     * @return PersistentTokenRepository
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        // 启动时创建表
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Bean
    @ConditionalOnMissingBean(LogoutSuccessHandler.class)
    public LogoutSuccessHandler logoutSuccessHandler(){
        return new CustomLogoutSuccessHandler(securityProperties.getBrowser().getSignOutUrl());
    }

}
