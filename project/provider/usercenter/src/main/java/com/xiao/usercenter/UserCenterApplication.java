package com.xiao.usercenter;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@EnableApolloConfig
@SpringBootApplication
@ComponentScan(basePackages = {"com.xiao"})
// @ComponentScan默认会扫描与配置类相同的包
// 因此Spring将会扫描这个包以及这个包下的所有子包，查找带有@Component注解的类,并由spring为其自动创建bean
public class UserCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserCenterApplication.class, args);
    }
}
