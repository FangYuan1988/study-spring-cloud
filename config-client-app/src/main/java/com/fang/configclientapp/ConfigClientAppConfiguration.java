package com.fang.configclientapp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
//中间不能有空格
@ConfigurationProperties(prefix="some")//找到some开头的资源
public class ConfigClientAppConfiguration {
    private String property;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
