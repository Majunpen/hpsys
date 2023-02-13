package com.hp.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.Stream;

/**
 * @Author: MJP
 * @Date: 2023/1/3 - 01 - 03 - 16:04
 * @Description: com.hp.controller
 * @version: 1.0
 */
@Controller
public class MainController {
    /*进入到登录页面*/
    @GetMapping("/index")
    public String index(){
        return "index";
    }

    /*系统主页面*/
    @GetMapping("/main")
    public String main(){
        return "main";
    }

    //欢迎页面
    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }
}
