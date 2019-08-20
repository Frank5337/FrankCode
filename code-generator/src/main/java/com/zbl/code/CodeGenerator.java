package com.zbl.code;

import com.baomidou.mybatisplus.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import org.springframework.util.ResourceUtils;

import java.util.Scanner;


/**
 * @Author: zbl
 * @Date: Created in 16:29 2019/8/20
 * @Description: 系统服务模块代码自动生成
 * @Version: $
 */
public class CodeGenerator {
    /**
     * 读取控制台内容
     */
    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(("请输入" + tip + "："));
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }


    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        String outputPath = null;
        try {

            outputPath = ResourceUtils.getURL("classpath:").getPath().replace(
                    "code-generator/target/classes/", "code-service/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String modelName = scanner("模块名");

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();

        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("zbl5337");
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/code?characterEncoding=utf8&useSSL=false");
        mpg.setDataSource(dsc);

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(outputPath);
        gc.setFileOverride(true);
        gc.setEnableCache(false);
        gc.setBaseResultMap(false);
        gc.setBaseColumnList(true);
        gc.setAuthor("zbl");

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        mpg.setGlobalConfig(gc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix(new String[]{"sys_", "res_", "uc_", "stdy_", "opera_", "fin_"});

        strategy.setNaming(NamingStrategy.remove_prefix_and_camel);  // 表名生成策略
        strategy.setFieldNaming(NamingStrategy.underline_to_camel);
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setEntityBuliderModel(true);
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();

        pc.setParent("com.zbl.code");
        pc.setEntity(modelName + ".pojo.po");
        pc.setMapper(modelName + ".dao");
        pc.setService(modelName + ".service");
        pc.setServiceImpl(modelName + ".service.impl");
        pc.setXml(modelName + ".dao");
        mpg.setPackageInfo(pc);

        // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
        // 至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
        TemplateConfig tc = new TemplateConfig();
        tc.setEntity("/mybatis-generator/model.java.vm");
        tc.setMapper("/mybatis-generator/mapper.java.vm");
        tc.setXml("/mybatis-generator/mapper.xml.vm");
        tc.setService("/mybatis-generator/service.java.vm");
        tc.setServiceImpl("/mybatis-generator/serviceImpl.java.vm");
        mpg.setTemplate(tc);

        // 执行生成
        mpg.execute();
    }
}