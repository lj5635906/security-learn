package com.security.learn.core.social.weixin.config;

import com.security.learn.core.properties.SecurityProperties;
import com.security.learn.core.properties.WeixinProperties;
import com.security.learn.core.social.weixin.connect.WeixinConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * ConditionalOnProperty 只有配置前缀为custom.security.social.qq后该配置才生效
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-21 14:17
 **/
@Configuration
@ConditionalOnProperty(prefix = "custom.security.social.weixin", name = "app-id")
public class WeixinAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        WeixinProperties weixinProperties = securityProperties.getSocial().getWeixin();
        return new WeixinConnectionFactory(weixinProperties.getProviderId(), weixinProperties.getAppId(), weixinProperties.getAppSecret());
    }
}
