package com.hp.exception;

import org.springframework.stereotype.Component;

/**
 * @Author: MJP
 * @Date: 2023/1/6 - 01 - 06 - 16:02
 * @Description: com.hp.exception
 * @version: 1.0
 */
//自定义异常类
public class ParamException extends RuntimeException{
    public ParamException(String message){
        super(message);
    }
}
