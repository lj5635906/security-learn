package com.security.learn.core.properties;

/**
 * 图形验证码
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2017-11-30 14:26
 **/
public class ImageCodeProperties extends SmsCodeProperties{

    public ImageCodeProperties(){
        setLength(4);
    }

    private int width =67;

    private int height= 23;

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
}
