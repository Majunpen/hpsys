package com.hp.config.date;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author: MJP
 * @Date: 2023/1/14 - 01 - 14 - 21:27
 * @Description: com.hp.config.date
 * @version: 1.0
 */
@Component
public class MytDateObjectHandler implements MetaObjectHandler {
    @Override //插入数据的时候日期处理类
    public void insertFill(MetaObject metaObject) {
        //添加创建时间和修改时间
        this.setFieldValByName("createTime", LocalDateTime.now(),metaObject);
        this.setFieldValByName("updateTime", LocalDateTime.now(),metaObject);
    }

    @Override //修改数据的时候日期处理类
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", LocalDateTime.now(),metaObject);
    }
}
