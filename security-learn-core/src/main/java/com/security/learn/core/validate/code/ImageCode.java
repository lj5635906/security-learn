package com.security.learn.core.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 图形验证码
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-17 16:40
 **/
public class ImageCode {

    /**
     * 图片
     */
    private BufferedImage image;
    /**
     * 验证码
     */
    private String code;
    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    /**
     *
     * @param image
     * @param code
     * @param expire 过期时间，秒
     */
    public ImageCode(BufferedImage image, String code, int expire) {
        this.image = image;
        this.code = code;
        // 当前时间加上 expire 秒
        this.expireTime = LocalDateTime.now().plusSeconds(expire);
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        this.image = image;
        this.code = code;
        this.expireTime = expireTime;
    }

    public boolean isExpired(){
        return LocalDateTime.now().isAfter(expireTime);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}
