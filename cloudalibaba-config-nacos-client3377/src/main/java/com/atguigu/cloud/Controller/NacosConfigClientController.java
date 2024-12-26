package com.atguigu.cloud.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class NacosConfigClientController {
    @Value("${config.info}")
    private String config;

    @GetMapping("/config/info")
    public String getConfig(){
        return "config:"+config+"   "+System.currentTimeMillis();
    }
}
