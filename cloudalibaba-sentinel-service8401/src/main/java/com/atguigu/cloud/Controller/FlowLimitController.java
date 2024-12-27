package com.atguigu.cloud.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA(){
        return "----A";
    }
    @GetMapping("/testB")
    public String testB(){
        return "----B";
    }
}
