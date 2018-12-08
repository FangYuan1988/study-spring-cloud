package com.fang.client;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class ClientApplication {
    //这个是Intellij的通病，但是这个错误不影响编译，相当于java里面的warning，当然也不影响运行.
    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private RestTemplateBuilder  restTemplateBuilder;

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }
    //访问这个程序, 会从服务中心获取,  需要服务的地址, 然后用get方法调用服务的controller
    // 因为服务有多个实例, 通过长轮询, 依次从不同实例调用服务l
    @RequestMapping("/")
    public String callService(){
            RestTemplate restTemplate = restTemplateBuilder.build();
            //从discovery获取服务的地址
            InstanceInfo  info  = eurekaClient.getNextServerFromEureka("service", false);
            String baseUrl = info.getHomePageUrl();
            //访问
            ResponseEntity<String> responseEntity  = restTemplate.exchange(baseUrl, HttpMethod.GET,null, String.class);
            return responseEntity.getBody();
    }
}
