package com.xiao.project_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@SpringBootApplication
@ComponentScan(basePackages = "com.xiao")
public class Project1Application {
    public static void main(String[] args) {
        SpringApplication.run(Project1Application.class, args);
    }
}
