package com.security.learn.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    /**
     * 请求验证码接口
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws Exception Exception
     */
    @GetMapping("/code/{type}")
    public void createCodeImage(HttpServletRequest request, HttpServletResponse response,
                                @PathVariable String type) throws Exception {
        validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
    }

}
