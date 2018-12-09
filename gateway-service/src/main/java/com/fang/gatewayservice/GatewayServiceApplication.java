package com.fang.gatewayservice;

import com.fang.gatewayservice.filter.AddRequestHeaderFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableZuulProxy//允许zuul
@EnableDiscoveryClient//允许作为discoveryServer的客户
@Configuration
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }

    @Bean//过滤器注入容器
    public AddRequestHeaderFilter addRequestHeaderFilter(){
        return new AddRequestHeaderFilter();
    }
}
