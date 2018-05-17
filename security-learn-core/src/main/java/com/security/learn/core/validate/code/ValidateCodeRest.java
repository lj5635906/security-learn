package com.security.learn.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 图形验证码相关接口
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-17 16:43
 **/
@RestController
public class ValidateCodeRest {

    public static final String session_key = "session_key_image_code";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private ValidateCodeGenerator validateCodeGenerator;

    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 创建验证码
        ImageCode imageCode = validateCodeGenerator.generate(new ServletWebRequest(request));
        // 存入session
        sessionStrategy.setAttribute(new ServletWebRequest(request),session_key,imageCode);
        // 输出图形验证码
        ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
    }



}
