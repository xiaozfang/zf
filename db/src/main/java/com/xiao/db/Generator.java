package com.xiao.db;


import lombok.extern.slf4j.Slf4j;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Generator {

    public static void main(String[] args) throws Exception {
        log.info("执行开始...");
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        File configFile = new File("db/src/main/resources/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        for (String warning : warnings) {
            log.info("注意：" + warning);
        }
        if (warnings.size() == 0) {
            log.info("生成实体成功");
        }
        log.info("执行结束...");
    }
}
