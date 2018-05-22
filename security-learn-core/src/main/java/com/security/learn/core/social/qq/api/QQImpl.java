package com.security.learn.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * QQ OAuth2 的API实现
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-21 11:38
 **/
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取openid的url
     */
    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
    /**
     * 获取用户信息的url
     */
    private static final String URL_USER_INFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
    private String appId;
    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();

    public QQImpl(String accessToken, String appId) {
        // 将 accessToken 参数 放到 请求查询参数上
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;

        String url = String.format(URL_GET_OPENID,accessToken);
        String result = getRestTemplate().getForObject(url,String.class);
        logger.info("QQ API 获取 openId : {}",result);

        this.openId = StringUtils.substringBetween(result,"\"openid\":\"","\"}");
    }

    @Override
    public QQUserInfo getUserInfo()   {
        String url = String.format(URL_USER_INFO,appId,openId);
        String result = getRestTemplate().getForObject(url,String.class);

        logger.info("QQ API 获取用户信息 : {}",result);
        QQUserInfo userInfo = null;
        try {
            userInfo = objectMapper.readValue(result,QQUserInfo.class);
            userInfo.setOpenId(this.openId);
            return userInfo;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("获取QQ用户信息出现异常");
        }
    }

}
