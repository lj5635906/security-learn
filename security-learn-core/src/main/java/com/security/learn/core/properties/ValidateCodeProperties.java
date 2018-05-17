package com.security.learn.core.properties;

/**
 * 验证码相关信息
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2017-11-30 14:27
 **/
public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }
}
