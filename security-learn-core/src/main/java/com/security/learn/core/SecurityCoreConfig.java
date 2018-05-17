package com.security.learn.core;

import com.security.learn.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 加载 SecurityProperties
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-17 15:02
 **/
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
}
