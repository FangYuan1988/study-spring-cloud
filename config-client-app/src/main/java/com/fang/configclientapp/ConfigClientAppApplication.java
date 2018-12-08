package com.fang.configclientapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@RefreshScope//使得bean更新配置
public class ConfigClientAppApplication {

    @Autowired
    private ConfigClientAppConfiguration properties;

    @Value("${some.other.property}") //获取global property
    private String someOtherProperty;

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientAppApplication.class, args);
    }


    //resuetMapping 没有
    @RequestMapping
    public String printConfig(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(properties.getProperty());
        stringBuilder.append(" || ");
        stringBuilder.append(someOtherProperty);
        return stringBuilder.toString();
    }
}
