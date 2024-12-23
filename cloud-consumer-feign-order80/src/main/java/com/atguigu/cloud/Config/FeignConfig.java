package com.atguigu.cloud.Config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    /**
     * 配置重试机制
     * @return
     */
    @Bean
    public Retryer myRetryer(){
        return Retryer.NEVER_RETRY;//Feign默认配置是不走重试策略的
//        return new Retryer.Default(100,1,3);
    }

    /**
     * 配置我们的日志
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
