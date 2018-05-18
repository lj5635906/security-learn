package com.security.learn.browser;

import com.security.learn.browser.authentication.SecurityAuthenticationFailureHandler;
import com.security.learn.browser.authentication.SecurityAuthenticationSuccessHandler;
import com.security.learn.core.properties.SecurityProperties;
import com.security.learn.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * ${DESCRIPTION}
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-17 11:29
 **/
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        // 启动时创建表
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Autowired
    private SecurityAuthenticationSuccessHandler securityAuthenticationSuccessHandler;
    @Autowired
    private SecurityAuthenticationFailureHandler securityAuthenticationFailureHandler;
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 验证码过滤器
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(securityAuthenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();

        http
                // 验证码过滤器在 UsernamePasswordAuthenticationFilter 之间执行
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                // 表单登陆
                .formLogin()
                    // 跳转登陆地址
                    .loginPage("/authentication/require")
                    // 自定义登陆请求
                    .loginProcessingUrl("/authentication/form")
                    .successHandler(securityAuthenticationSuccessHandler)
                    .failureHandler(securityAuthenticationFailureHandler)
                .and()
                    // 记住我相关
                    .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                    .userDetailsService(userDetailsService)
                .and()
                    .authorizeRequests()
                    // /demo-signIn.html 不需要身份认证
                    .antMatchers("/authentication/require",
                            securityProperties.getBrowser().getLoginPage(),
                            "/code/*").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }
}
