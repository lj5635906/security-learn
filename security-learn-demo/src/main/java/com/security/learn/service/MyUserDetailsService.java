package com.security.learn.service;

import com.security.learn.entity.UserInfo;
import com.security.learn.reposityory.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 用户认证相关
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-17 13:54
 **/
@Component
public class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserInfoRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("表单登陆用户名： {}", username);
        return buildUser(null, username);
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        logger.info("社交登陆用户Id ： {}", userId);
        return buildUser(userId, null);
    }

    private SocialUserDetails buildUser(String userId, String username) {
        UserInfo userInfo = null;
        if (StringUtils.isEmpty(userId)) {
            userInfo = repository.findByUsernameOrMobile(username, username);
        } else {
            repository.findOne(Long.valueOf(userId));
        }
        if (null == userInfo) {
            throw new UsernameNotFoundException("用户信息不存在.");
        }
        // 账户是否可用
        boolean enabled = true;
        if (userInfo.getDeleteFlag().intValue() != 0) {
            enabled = false;
        }
        // 账户是否过期
        boolean accountNonExpired = true;
        // 密码是否过期
        boolean credentialsNonExpired = true;
        // 账户是否锁定
        boolean accountNonLocked = true;
        if (userInfo.getStatus().intValue() == 2) {
            accountNonLocked = false;
        }
        // 授权
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
        return new SocialUser(userInfo.getUsername(), userInfo.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
