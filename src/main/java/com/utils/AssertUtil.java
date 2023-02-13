package com.utils;

import com.hp.exception.ParamException;



/**
 * @Author: MJP
 * @Date: 2023/1/6 - 01 - 06 - 16:04
 * @Description: com.utils
 * @version: 1.0
 */
public class AssertUtil {
    public static void isTrue(Boolean flag,String msg){
        if(flag){
            throw new ParamException(msg);
        }
    }
}
