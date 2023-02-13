package com.hp.resp;

import jdk.nashorn.internal.ir.debug.ASTWriter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: MJP
 * @Date: 2023/1/5 - 01 - 05 - 20:52
 * @Description: com.hp.resp
 * @version: 1.0
 */
@Data
@NoArgsConstructor //无参的构造方法
@AllArgsConstructor //有参的构造方法
public class RespBean {
    private int code; //请求是否成功
    private String message; //返回的信息
    private Object data; //返回的数据
    public static RespBean success(){
        return new RespBean(200,"成功",null);
    }
    public static RespBean success(String message){
        return new RespBean(200,message,null);
    }
    public static RespBean success(String message ,Object data){
        return new RespBean(200,message,data);
    }
    public static RespBean error(){
        return new RespBean(500,"失败",null);
    }
    public static RespBean error(String message ){
        return new RespBean(500,message,null);
    }

}
