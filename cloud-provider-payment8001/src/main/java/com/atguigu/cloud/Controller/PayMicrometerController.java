package com.atguigu.cloud.Controller;

import cn.hutool.core.util.IdUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayMicrometerController {

    @GetMapping("/pay/micrometer/{id}")
    public String myMicrometer(@PathVariable("id")Integer id){
        return "hello"+id+"\n"+"服务返回"+ IdUtil.simpleUUID();
    }
}
