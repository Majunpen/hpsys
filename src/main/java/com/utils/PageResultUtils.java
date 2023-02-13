package com.utils;

import com.hp.pojo.Dept;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: MJP
 * @Date: 2023/1/9 - 01 - 09 - 16:44
 * @Description: com.utils
 * @version: 1.0
 */
public class PageResultUtils {
    public static Map<String ,Object> getResult(long total, List<?> list){
        Map<String,Object> map= new HashMap<>();
        map.put("code","0");//对于layui,要求返回的code必须是0
        map.put("msg","");//message为空
        map.put("count",total);//总的信息条数
        map.put("data",list); //一页数据
        return map;
    }
}
