package com.atguigu.cloud.Controller;

import com.atguigu.cloud.Api.PayFeignApi;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderCircuitController {
    @Autowired
    private PayFeignApi payFeignApi;

    @GetMapping("/feign/pay/get/circuit/{id}")
    @CircuitBreaker(name = "cloud-payment-service",fallbackMethod = "myCircuitFallback")
    public String myCircuitBreaker(@PathVariable("id")Integer id){
        return payFeignApi.myCircuit(id);
    }
    //这里就是服务降级之后的兜底方法
    public String myCircuitFallback(Integer id,Throwable t){
        return "系统繁忙，请稍后再试";
    }
}
