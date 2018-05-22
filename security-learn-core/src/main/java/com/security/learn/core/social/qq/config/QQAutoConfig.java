package com.security.learn.core.social.qq.config;

import com.security.learn.core.properties.QQProperties;
import com.security.learn.core.properties.SecurityProperties;
import com.security.learn.core.social.qq.connect.QQConnectionFactory;
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
@ConditionalOnProperty(prefix = "custom.security.social.qq",name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter{

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqProperties = securityProperties.getSocial().getQq();
        return new QQConnectionFactory(qqProperties.getProviderId(),qqProperties.getAppId(),qqProperties.getAppSecret());
    }
}
