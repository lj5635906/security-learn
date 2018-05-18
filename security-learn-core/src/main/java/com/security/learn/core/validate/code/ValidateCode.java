package com.security.learn.core.validate.code;

import java.time.LocalDateTime;

/**
 * 短信验证码
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-17 16:40
 **/
public class ValidateCode {

    /**
     * 验证码
     */
    private String code;
    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    /**
     * @param code
     * @param expire 过期时间，秒
     */
    public ValidateCode(String code, int expire) {
        this.code = code;
        // 当前时间加上 expire 秒
        this.expireTime = LocalDateTime.now().plusSeconds(expire);
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
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
