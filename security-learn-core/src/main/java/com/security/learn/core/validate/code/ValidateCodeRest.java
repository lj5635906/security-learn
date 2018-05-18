package com.security.learn.core.validate.code;

import com.security.learn.core.validate.code.image.ImageCode;
import com.security.learn.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
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
    private ValidateCodeGenerator imageCodeGenerator;

    @Autowired
    private ValidateCodeGenerator smsCodeGenerator;

    @Autowired
    private SmsCodeSender smsCodeSender;

    /**
     * 图形验证码接口
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException
     */
    @GetMapping("/code/image")
    public void createCodeImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 创建验证码
        ImageCode imageCode = (ImageCode) imageCodeGenerator.generate(new ServletWebRequest(request));
        // 存入session
        sessionStrategy.setAttribute(new ServletWebRequest(request),session_key,imageCode);
        // 输出图形验证码
        ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
    }

    /**
     * 短信验证码接口
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException
     */
    @GetMapping("/code/sms")
    public void createCodeSms(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
        // 创建验证码
        ValidateCode smsCode = smsCodeGenerator.generate(new ServletWebRequest(request));
        // 存入session
        sessionStrategy.setAttribute(new ServletWebRequest(request),session_key,smsCode);
        // 发送验证码
        String mobile = ServletRequestUtils.getRequiredStringParameter(request,"mobile");
        smsCodeSender.send(mobile,smsCode.getCode());
    }


}
