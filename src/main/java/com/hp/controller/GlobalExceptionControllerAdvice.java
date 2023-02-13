package com.hp.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.util.zip.Adler32;

/**
 * @Author: MJP
 * @Date: 2023/1/6 - 01 - 06 - 22:19
 * @Description: com.hp.controller
 * @version: 1.0
 */
@ControllerAdvice
public class GlobalExceptionControllerAdvice {
    @ExceptionHandler(value = Exception.class)
    public Object globalExceptionHandler(Exception e, HttpServletRequest request){
        Boolean isNow = (Boolean) request.getAttribute("isNow");
        if (isNow){
            //响应为视图
            request.setAttribute("message","系统异常");
            return  "error";
        }else {
            //响应为json
            ModelAndView mv = new ModelAndView(new MappingJackson2JsonView());
            mv.addObject("code",500);
            mv.addObject("message",e.getMessage());
            return mv;
        }

    }
}
