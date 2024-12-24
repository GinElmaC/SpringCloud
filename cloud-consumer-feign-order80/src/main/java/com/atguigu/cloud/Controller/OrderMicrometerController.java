package com.atguigu.cloud.Controller;

import com.atguigu.cloud.Api.PayFeignApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderMicrometerController {
    @Autowired
    private PayFeignApi payFeignApi;

    @GetMapping("/feign/micrometer/{id}")
    public String myMicrometer(@PathVariable("id")Integer id){
        return payFeignApi.myMicrometer(id);
    }
}
