package com.hp.generator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Author: yan.
 * @Description: mybatis-plus的逆向工程
 * @Date:Created in 2022/12/30 14:37.
 * @Modified By:
 */
public class CodeGenerator {
    /**
     * 读取控制台中表的名字，逆向生成代码
     * @param tip 要逆向生成的表名
     * @return
     */
    public static String scanner(String tip){
        //创建Scanner对象
        Scanner scanner = new Scanner(System.in);
        //创建stringBuilder对象
        StringBuilder heple = new StringBuilder();
        heple.append("请输入" + tip + ":");
        System.out.println(heple.toString());
        if(scanner.hasNext()){
            String ipt = scanner.next();
            if(StringUtils.isNotBlank(ipt)){
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "!");
    }

    /**
     * 代码生成器
     * @param args
     */
    public static void main(String[] args) {
        //1.创建代码生成器对象
        AutoGenerator mpg = new AutoGenerator();
        //2.全局配置
        GlobalConfig gc = new GlobalConfig();
        String propertyPath = System.getProperty("user.dir");//获取到项目的路径
        gc.setOutputDir(propertyPath +"/src/main/java");
        //作者
        gc.setAuthor("mjp");
        //打开输出目录
        gc.setOpen(false);
        //xml开启生成BaseResultMap
        gc.setBaseResultMap(true);
        //xml开启BaseColumnList
        gc.setBaseColumnList(true);
        //实体类上添加swagger注解
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        //3.数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUrl("jdbc:mysql://localhost:3306/hp?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);

        //4.包设置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.hp")
                .setEntity("pojo")
                .setMapper("mapper")
                .setService("service")
                .setServiceImpl("service.impl")
                .setController("controller");
        mpg.setPackageInfo(pc);

        //5.自定义设置
        InjectionConfig cfg = new InjectionConfig(){

            @Override
            public void initMap() {

            }
        };
        //模板引擎如果是freemarkerr
        String templatePath = "/templates/mapper.xml.ftl";

        //自定义输出配置
        ArrayList<FileOutConfig> focList = new ArrayList<>();
        //自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                //自定义输出文件名,如果Entity设置了后缀，此处注意xml的名字会跟着发生变化
                return  propertyPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        //配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);
        //策略配置
        StrategyConfig strategy = new StrategyConfig();
        //数据表映射到实体的命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体---驼峰写法
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据表映射到字段名--驼峰写法
        //lombok模型
        strategy.setEntityLombokModel(true);

        //在controller上生成@RestController
        strategy.setRestControllerStyle(true);
        strategy.setInclude(scanner("表名，多个表之间用都好分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        //表前缀设置
        strategy.setTablePrefix("t_");
        mpg.setStrategy(strategy);

        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}