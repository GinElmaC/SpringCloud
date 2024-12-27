package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//springboot启动项
//服务发现与注册
@SpringBootApplication
@EnableDiscoveryClient
public class Main9527 {

    public static void main(String[] args) {
        SpringApplication.run(Main9527.class,args);
    }
}