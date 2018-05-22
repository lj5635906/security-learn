package com.security.learn.core.social.weixin.api;

/**
 * 微信登陆获取用户信息接口
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-22 14:41
 **/
public interface Weixin {

    WeixinUserInfo getWeixinUserInfo(String openId);

}
