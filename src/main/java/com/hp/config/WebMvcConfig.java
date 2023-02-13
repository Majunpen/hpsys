package com.hp.config;

import com.hp.interceptor.HpSysPreHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: MJP
 * @Date: 2023/1/6 - 01 - 06 - 22:11
 * @Description: com.hp.config
 * @version: 1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private HpSysPreHandler hpSysPreHandler;
    //添加自定义拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(hpSysPreHandler).addPathPatterns("");
    }
}
