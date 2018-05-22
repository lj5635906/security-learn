package com.security.learn.core.social.weixin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.learn.core.social.qq.api.QQUserInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-22 14:42
 **/
public class WeixinImpl extends AbstractOAuth2ApiBinding implements Weixin {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private ObjectMapper objectMapper = new ObjectMapper();
    /**
     * 获取用户信息的url
     */
    private static final String URL_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?openid=%s";

    /**
     * @param accessToken
     */
    public WeixinImpl(String accessToken) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    }

    /**
     * 默认注册的StringHttpMessageConverter字符集为ISO-8859-1，而微信返回的是UTF-8的，所以覆盖了原来的方法。
     */
    @Override
    protected List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
        messageConverters.remove(0);
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return messageConverters;
    }

    @Override
    public WeixinUserInfo getWeixinUserInfo(String openId) {
        String url = String.format(URL_GET_USER_INFO,openId);
        String result = getRestTemplate().getForObject(url,String.class);

        if(StringUtils.contains(result, "errcode")) {
            return null;
        }

        logger.info("微信 API 获取用户信息 : {}",result);
        WeixinUserInfo userInfo = null;
        try {
            userInfo = objectMapper.readValue(result,WeixinUserInfo.class);
            userInfo.setOpenid(openId);
            return userInfo;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("获取微信用户信息出现异常");
        }
    }
}
