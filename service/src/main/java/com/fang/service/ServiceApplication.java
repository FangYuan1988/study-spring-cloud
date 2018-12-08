package com.fang.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//discovery的客户
@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class ServiceApplication {
    //获取配置文件中的值, 注入
    @Value("${service.instance.name}")
    private String instance;

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

    @RequestMapping("/")
    public String message(){
            return "Hello From" + instance;
    }
}