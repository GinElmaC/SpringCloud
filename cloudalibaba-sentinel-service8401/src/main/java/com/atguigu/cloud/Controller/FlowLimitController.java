package com.atguigu.cloud.Controller;

import com.atguigu.cloud.Service.FlowLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

//测试流控模式
@RestController
public class FlowLimitController {
    //测试直接模式和关联模式
    @GetMapping("/testA")
    public String testA(){
        return "----A";
    }
    @GetMapping("/testB")
    public String testB(){
        return "----B";
    }

    //测试链路模式,阈值达到后对c限流，对d不管
    @Autowired
    private FlowLimitService flowLimitService;

    @GetMapping("/testC")
    public String testC(){
        flowLimitService.common();
        return "----C";
    }
    @GetMapping("/testD")
    public String testD(){
        flowLimitService.common();
        return "----D";
    }

    //测试流控效果  排队等待模式
    @GetMapping("/testE")
    public String testE(){
        System.out.println("流控效果：排队等待");
        return "----E";
    }

    //测试Sentinel服务熔断   慢调用比例
    @GetMapping("/testF")
    public String testF(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("测试慢调用，成功沉睡1s");
        return "----F";
    }

    //测试Sentinel服务熔断   异常比例
    @GetMapping("/testG")
    public String testG(){
        int age = 10/0;
        return "---testG";
    }
}
