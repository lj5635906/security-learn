package com.security.learn.core.properties;

/**
 * 短信验证码
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2017-11-30 14:26
 **/
public class SmsCodeProperties {

    private int length = 6;

    private int expireIn = 60;

    private String url;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
