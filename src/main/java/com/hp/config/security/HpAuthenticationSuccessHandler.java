package com.hp.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hp.resp.RespBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: MJP
 * @Date: 2023/1/5 - 01 - 05 - 20:43
 * @Description: com.hp.config.security
 * @version: 1.0
 */
@Component
public class HpAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        //成功后的处理的方式
        response.setContentType("application/json;charset=UTF-8");//告诉浏览器，我们返回的是json
        response.getWriter().write(new ObjectMapper().writeValueAsString(RespBean.success("登录成功")));

    }
}
