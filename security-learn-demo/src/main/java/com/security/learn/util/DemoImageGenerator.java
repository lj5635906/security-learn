package com.security.learn.util;

import com.security.learn.core.validate.code.ImageCode;
import com.security.learn.core.validate.code.generator.ValidateCodeGenerator;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 自定义图形验证码生成规则
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-17 17:35
 **/
//@Component("imageCodeGenerator")
public class DemoImageGenerator implements ValidateCodeGenerator {

    @Override
    public ImageCode generate(ServletWebRequest request) {
        System.out.println("更高级的图形验证码生成规则");
        return null;
    }
}
