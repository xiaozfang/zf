//package com.xiao.database.config.datasource;
//
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 创建数据库Bean
// */
//
//@Configuration
//@MapperScan("com.xiao.dao")
//public class DataSourceConfigs {
//
//    @Bean(name = "first")
//    @ConfigurationProperties(prefix = "spring.datasource.first")
//    public DataSource FIRST_DB() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "second")
//    @ConfigurationProperties(prefix = "spring.datasource.second")
//    public DataSource SECOND_DB() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    // 添加新的数据源
//    // ...
//
//
//    @Bean(name = "dataSource")
//    @Primary
//    public DynamicDataSource dataSource(@Qualifier("first") DataSource datasource1, @Qualifier("second") DataSource datasource2) {
//        DynamicDataSource dataSource = new DynamicDataSource();
//        Map<Object, Object> targetDataSources = new HashMap<>();
//
//        targetDataSources.put(DataSourceTypes.first, datasource1);
//        targetDataSources.put(DataSourceTypes.SECOND, datasource2);
//
//        // 将数据源放入选择器中
//        dataSource.setTargetDataSources(targetDataSources);
//        // 默认的datasource设置为dataSourceDB1
//        dataSource.setDefaultTargetDataSource(datasource1);
//
//        return dataSource;
//    }
//
//    /**
//     * 根据数据源创建SqlSessionFactory
//     */
//    @Bean("sqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactory() throws Exception {
//
//        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
//        fb.setDataSource(dataSource(FIRST_DB(), SECOND_DB()));
//        //扫描持久层
//        fb.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResources("classpath:/mapper/*.xml"));
//        return fb.getObject();
//    }
//
//}
