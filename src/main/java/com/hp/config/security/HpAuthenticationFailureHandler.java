package com.hp.config.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hp.resp.RespBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: MJP
 * @Date: 2023/1/5 - 01 - 05 - 21:05
 * @Description: com.hp.config.security
 * @version: 1.0
 */
@Component
public class HpAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");//返回的是json
        response.getWriter().write(new ObjectMapper().writeValueAsString(RespBean.error("用户名或密码错误")));
    }
}
