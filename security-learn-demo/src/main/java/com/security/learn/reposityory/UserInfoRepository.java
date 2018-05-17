package com.security.learn.reposityory;

import com.security.learn.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ${DESCRIPTION}
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-03-22 17:16
 **/
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {

    UserInfo findByUsername(String username);

}
