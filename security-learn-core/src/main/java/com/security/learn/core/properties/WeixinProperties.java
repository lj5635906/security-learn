package com.security.learn.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * 微信登陆相关配置
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-21 14:14
 **/
public class WeixinProperties extends SocialProperties {

    private String providerId = "weixin";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
