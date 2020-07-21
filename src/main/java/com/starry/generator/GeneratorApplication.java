package com.starry.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class GeneratorApplication {
    public static void main(String[] args) {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setIdType(IdType.AUTO);
        globalConfig.setOutputDir(System.getProperty("user.dir") + "src/main/resources/generatorCode");
        globalConfig.setFileOverride(true);
        globalConfig.setOpen(false);
        globalConfig.setEnableCache(false);
        globalConfig.setAuthor("Lewis");
        globalConfig.setKotlin(false);
        globalConfig.setSwagger2(false);
        globalConfig.setActiveRecord(false);
        globalConfig.setBaseResultMap(false);
        globalConfig.setBaseColumnList(false);
        globalConfig.setDateType(DateType.ONLY_DATE);
        globalConfig.setEntityName("%sEntity");
        globalConfig.setMapperName("%sMapper");
        globalConfig.setXmlName("%sMapper");
        globalConfig.setServiceName("%sService");
        globalConfig.setServiceImplName("%sServiceImpl");
        globalConfig.setControllerName("%sController");

        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/admin?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("123456");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setSkipView(false);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setTablePrefix("t_");

        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setController("com.starry.controller");
        packageConfig.setEntity("com.starry.entity");
        packageConfig.setMapper("com.starry.dao");
        packageConfig.setService("com.starry.service");
        packageConfig.setServiceImpl("com.starry.service.impl");
        packageConfig.setXml("com.starry.dao");

        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setDataSource(dataSourceConfig);
        autoGenerator.setGlobalConfig(globalConfig);
        autoGenerator.setPackageInfo(packageConfig);
        autoGenerator.setStrategy(strategyConfig);

        autoGenerator.execute();
    }
}
