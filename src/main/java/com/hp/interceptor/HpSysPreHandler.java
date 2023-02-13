package com.hp.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Author: MJP
 * @Date: 2023/1/6 - 01 - 06 - 21:48
 * @Description: com.hp.interceptor
 * @version: 1.0
 */
@Component  //统一异常处理拦截器
public class HpSysPreHandler implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //在controller中的方法执行前执行（如：权限拦截）
        if (!(handler instanceof HandlerMethod)){
            return true;//放行
        }
        HandlerMethod handlerMethod =(HandlerMethod) handler;
        //获取访问资源（这个方法）
        Method method = handlerMethod.getMethod();
        //判断方法的返回结果
        boolean isString = method.getReturnType().equals(String.class);
        //判断方法上面是否标记有@ResponseBody
        boolean isResponseBody = !method.isAnnotationPresent(ResponseBody.class);
        //判断
        boolean isRestController = handlerMethod.getBeanType().isAnnotationPresent(RestController.class);
        boolean isNow = isRestController == true ? false : (isString && isResponseBody);
        request.setAttribute("isNow",isNow);
        //放行
        return true;
    }

    @Override //在controller方法中执行，（如：方法的增强）
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override //在controller方法执行后执行，（如，日志，事务管理）
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
