package com.security.learn.core.properties;

/**
 * 图形验证码
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2017-11-30 14:26
 **/
public class ImageCodeProperties {

    private int width =67;

    private int height= 23;

    private int length = 4;

    private int expireIn = 60;

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

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
}
