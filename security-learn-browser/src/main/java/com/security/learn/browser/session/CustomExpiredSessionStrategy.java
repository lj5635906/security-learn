package com.security.learn.browser.session;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.learn.core.support.SimpleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * 控制session并发控制
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-05-23 11:32
 **/
public class CustomExpiredSessionStrategy implements SessionInformationExpiredStrategy {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        String message = "session已失效";
        message = message + "，有可能是并发登录导致的";
        event.getResponse().setContentType("application/json;charset=UTF-8");
        event.getResponse().setStatus(HttpStatus.UNAUTHORIZED.value());
        event.getResponse().getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(message)));
    }
}
