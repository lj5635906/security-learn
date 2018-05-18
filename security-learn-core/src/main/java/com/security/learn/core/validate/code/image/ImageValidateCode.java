package com.security.learn.core.validate.code.image;

import com.security.learn.core.validate.code.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 图形验证码
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-17 16:40
 **/
public class ImageValidateCode extends ValidateCode {

    /**
     * 图片
     */
    private BufferedImage image;

    /**
     *
     * @param image
     * @param code
     * @param expire 过期时间，秒
     */
    public ImageValidateCode(BufferedImage image, String code, int expire) {
        super(code, expire);
        this.image = image;
    }

    public ImageValidateCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.image = image;
    }


    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
