package com.hp.config.securitytag;

import freemarker.ext.jsp.TaglibFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

/**
 * @Author: MJP
 * @Date: 2023/1/15 - 01 - 15 - 22:24
 * @Description: com.hp.config.securitytag
 * @version: 1.0
 */

@Configuration //变成xml
public class HpSecurityTldConfig {
    @Autowired
    private FreeMarkerConfigurer configurer;
    @PostConstruct //作用：实在实体类初始化的时候，就会执行注解标注的方法
    public void freeMarkerConfig(){
        ArrayList<String> tlds = new ArrayList<>();
        tlds.add("/tags/security.tld"); //设置自定义标签库
        TaglibFactory taglibFactory = configurer.getTaglibFactory();
        taglibFactory.setClasspathTlds(tlds); //应用我们自己的标签库
        if(taglibFactory.getObjectWrapper() ==null){
            taglibFactory.setObjectWrapper(configurer.getConfiguration().getObjectWrapper());
        }

    }

}
