package com.xiao.database.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSONObject;
import com.xiao.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@MapperScan("com.xiao.dao")
public class DynamicDataSourceInit {

    @Value("${default.dbname:first}")
    private String DEFAULT_DB_NAME;

    @Value("${dbconfig:null}")
    private String DB_CONFIG;

    @Bean(name = "dataSource")
    @Primary
    public DynamicDataSource dataSource() {


        DynamicDataSource dataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();

        if ("null".equals(DB_CONFIG)){
            DB_CONFIG = readDBConfig();
        }
        if (StringUtils.isEmptyOrNull(DB_CONFIG)) {
            log.error("数据库初始化异常，请检查数据库配置文件");
            System.exit(0);
        }
        JSONObject json = JSONObject.parseObject(DB_CONFIG);
        for (Map.Entry<String, Object> entry : json.entrySet()) {
            DataSource db = buildDataSource(entry);
            // 将多个数据源放入选择器中
            targetDataSources.put(entry.getKey().toLowerCase(), db);
            if (DEFAULT_DB_NAME.equals(entry.getKey().toLowerCase())) {
                // 默认的datasource设置为dataSourceDB1
                dataSource.setDefaultTargetDataSource(db);
                log.info("设置默认数据源：" + entry.getKey());
            }
        }
        dataSource.setTargetDataSources(targetDataSources);
        return dataSource;
    }

    private DataSource buildDataSource(Map.Entry<String, Object> dbconfig) {
        DbItem item = JSONObject.parseObject(dbconfig.getValue().toString(), DbItem.class);

        // 现在使用的是Druid连接池，如果使用HikariCP连接池，则设置type(HikariDataSource.class)，并进行相关设置
        DruidDataSource db = DataSourceBuilder.create()
                .url(item.getUrl())
                .driverClassName(item.getDriverName())
                .type(DruidDataSource.class)
                .username(item.getUsername())
                .password(item.getPassword()).build();
        if (db != null) {
            //配置初始化大小、最小、最大
            db.setInitialSize(5);
            //最小连接池数量
            db.setMinIdle(1);
            //最大连接池数量
            db.setMaxActive(30);
            //获取连接时最大等待时间，单位毫秒。配置了maxWait之后，缺省启用公平锁，
            //并发效率会有所下降，如果需要可以通过配置useUnfairLock属性为true使用非公平锁。
            db.setMaxWait(10 * 1000);

            //建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，
            //如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
            db.setTestWhileIdle(true);

            //申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
            db.setTestOnBorrow(false);

            //归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
            db.setTestOnReturn(false);
            //
            // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
            // 有两个含义：
            // 1) Destroy线程会检测连接的间隔时间
            // 2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明
            // 1.testWhileIdle的空闲时间判断依据
            // 2.destroyThread检查的间隔时间（为什么这两个会用一个时间，大概是testWhileIdle进行主动检测后，
            //   destroy的间隔就没必要小于这个时间了。destroy 依赖 min/mixEvictableIdleTimeMillis 时间进行链接驱逐)
            db.setTimeBetweenEvictionRunsMillis(60 * 1000);
            //配置一个连接在池中最小生存的时间，单位是毫秒
            db.setMinEvictableIdleTimeMillis(300 * 1000);

            //连接检查超时，如果执行检查sql超过这个时间认为失败
            db.setValidationQueryTimeout(2000);

            db.setLogAbandoned(true);
            db.setRemoveAbandoned(true);
            // 连接池为了防止程序从池里取得连接后忘记归还的情况, 而提供了一些参数来设置一个租期,
            // 意思是通过datasource.getConnontion() 取得的连接必须在removeAbandonedTimeout这么多秒内调用close()
            db.setRemoveAbandonedTimeoutMillis(180 * 1000);
        }
        log.info("初始化数据源：" + dbconfig.getKey() +" " + item.getUrl());
        return db;
    }


    private String readDBConfig() {
        File file = new File("component-database/src/main/resources/db.json");
        try {
            return FileUtils.readFileToString(file,"UTf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
