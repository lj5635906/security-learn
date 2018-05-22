package com.security.learn.core.social.qq.connect;

import com.security.learn.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * QQConnectionFactory
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-21 13:49
 **/
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId,appSecret), new QQAdapter());
    }
}
