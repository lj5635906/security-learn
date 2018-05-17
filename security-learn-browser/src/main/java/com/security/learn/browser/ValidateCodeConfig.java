package com.security.learn.browser;

import com.security.learn.core.properties.SecurityProperties;
import com.security.learn.core.validate.code.ImageGenerator;
import com.security.learn.core.validate.code.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 验证码相关配置
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-17 17:22
 **/
@Configuration
public class ValidateCodeConfig {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * ConditionalOnMissingBean 先从容器中找名为"imageCodeGenerator"的bean，
     * 如有已经存在，就不执行下面的方法；
     * 如果不存在，才执行下面的实例化方法
     *
     * ConditionalOnMissingBean --> 已增量的方式去适应变化，引用时自定义来覆盖已有的类
     *
     * @return ValidateCodeGenerator
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator() {
        ImageGenerator imageGenerator = new ImageGenerator();
        imageGenerator.setSecurityProperties(securityProperties);
        return imageGenerator;
    }
}
