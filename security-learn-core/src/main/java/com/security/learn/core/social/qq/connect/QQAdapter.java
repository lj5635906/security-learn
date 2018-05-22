package com.security.learn.core.social.qq.connect;

import com.security.learn.core.social.qq.api.QQUserInfo;
import com.security.learn.core.social.qq.api.QQ;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * QQ用户信息适配器
 * QQ返回的用户信息 适配为 标准的Connection用户信息
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-21 13:40
 **/
public class QQAdapter implements ApiAdapter<QQ> {

    /**
     * 测试当前API是否可用
     */
    @Override
    public boolean test(QQ qq) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ qq, ConnectionValues values) {
        QQUserInfo userInfo = qq.getUserInfo();

        // 显示名称
        values.setDisplayName(userInfo.getNickname());
        // 头像
        values.setImageUrl(userInfo.getFigureurl_qq_1());
        // 个人主页
        values.setProfileUrl(null);
        // 服务提供商的唯一标识
        values.setProviderUserId(userInfo.getOpenId());
    }

    /**
     * 用户绑定、解绑
     */
    @Override
    public UserProfile fetchUserProfile(QQ qq) {
        return null;
    }

    /**
     * 更新微博
     */
    @Override
    public void updateStatus(QQ qq, String s) {
        // do nothing
    }
}
