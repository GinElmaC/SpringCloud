package com.atguigu.cloud.Controller;

import com.atguigu.cloud.Api.PayFeignApi;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
public class OrderCircuitController {
    @Autowired
    private PayFeignApi payFeignApi;

    /**
     * 测试断路器
     * @param id
     * @return
     */
    @GetMapping("/feign/pay/get/circuit/{id}")
    @CircuitBreaker(name = "cloud-payment-service",fallbackMethod = "myCircuitFallback")
    public String myCircuitBreaker(@PathVariable("id")Integer id){
        return payFeignApi.myCircuit(id);
    }
    //这里就是服务降级之后的兜底方法
    public String myCircuitFallback(Integer id,Throwable t){
        return "系统繁忙，请稍后再试";
    }

    /**
     * 测试舱壁隔离
     */
//    @GetMapping("/feign/pay/get/bulkhead/{id}")
//    @Bulkhead(name = "cloud-payment-service",fallbackMethod = "myBulkheadFallback",type = Bulkhead.Type.SEMAPHORE)
//    public String myBulkhead(@PathVariable("id")Integer id){
//        return payFeignApi.myCircuit(id);
//    }
//    //这里就是服务降级之后的兜底方法
//    public String myBulkheadFallback(Integer id,Throwable t){
//        return "隔板超出限制，系统繁忙，请稍后再试";
//    }

    /**
     * 测试线程池舱壁隔离
     * @param id
     * @return
     */
    @GetMapping("/feign/pay/get/bulkhead/{id}")
    @Bulkhead(name = "cloud-payment-service",fallbackMethod = "myBulkheadPoolFallback",type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<String> myBulkheadThreadPool(@PathVariable("id")Integer id){
        System.out.println(Thread.currentThread().getName()+"\t"+"进入");
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"\t"+"准备离开");

        return CompletableFuture.supplyAsync(()-> payFeignApi.myCircuit(id)+"\t"+"Bulkhead.Type.THREADPOOL");
    }
    //这里就是服务降级之后的兜底方法
    public CompletableFuture<String> myBulkheadPoolFallback(Integer id,Throwable t){
        return CompletableFuture.supplyAsync(()->"Bulkhead.Type.THREADPOOL方法降级，系统繁忙");
    }

    /**
     * 测试限流
     * @param id
     * @return
     */
    @GetMapping(value = "/feign/pay/ratelimit/{id}")
    @RateLimiter(name = "cloud-payment-service",fallbackMethod = "myRatelimitFallback")
    public String myBulkhead(@PathVariable("id") Integer id)
    {
        return payFeignApi.myRatelimit(id);
    }
    public String myRatelimitFallback(Integer id,Throwable t)
    {
        return "你被限流了，禁止访问/(ㄒoㄒ)/~~";
    }
}
