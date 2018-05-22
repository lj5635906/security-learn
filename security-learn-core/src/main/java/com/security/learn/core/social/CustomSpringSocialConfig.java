package com.security.learn.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * ${DESCRIPTION}
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-21 16:50
 **/
public class CustomSpringSocialConfig extends SpringSocialConfigurer {

    /**
     * 第三方登陆拦截的url
     */
    private String filterProcessesUrl;
    /**
     * 注册地址
     */
    private String signUpUrl;

    public CustomSpringSocialConfig(String filterProcessesUrl,String signUpUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
        this.signUpUrl=signUpUrl;
    }

    /**
     * 重写 postProcess 方法，
     */
    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter)super.postProcess(object);
        filter.setFilterProcessesUrl(this.filterProcessesUrl);
        filter.setSignupUrl(signUpUrl);
        return (T) filter;
    }

}
