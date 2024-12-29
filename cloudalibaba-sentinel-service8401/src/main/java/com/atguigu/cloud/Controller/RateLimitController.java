package com.atguigu.cloud.Controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试@SentinelResource注解
 */
@RestController
@Slf4j
public class RateLimitController {
    //1
    @GetMapping("/rateLimit/byurl")
    public String byUrl(){
        return "按rest地址限流";
    }

    //2
    @GetMapping("/rateLimit/byresource")
    @SentinelResource(value = "byResourceSentinelResource",blockHandler = "byResourceblockHandler")
    public String byResource(){
        return "按照resource名称限流";
    }
    public String byResourceblockHandler(BlockException blockException){
        return "服务不可用，@SentinelResource自定义的限流处理";
    }

    //3
    @GetMapping("/rateLimit/doaction/{id}")
    @SentinelResource(value = "doActionSentinelResource",blockHandler = "doActionblockHandler",fallback = "doActionfallback")
    public String doAction(@PathVariable("id")Integer id){
        if(id == 0){
            throw new RuntimeException("id不可以为0");
        }
        return "doAction成功运行";
    }
    public String doActionblockHandler(@PathVariable("id")Integer id,BlockException blockException){
        log.error("sentinel配置自定义限流:{}",blockException);
        return "sentinel自定义限流";
    }
    public String doActionfallback(@PathVariable("id")Integer id,Throwable e){
        log.error("fallback方法:{}",e);
        return "fallback方法执行";
    }

    //测试热点规则
    @GetMapping("/rateLimit/doaction/hot")
    @SentinelResource(value = "testHotRuleSentinelResource",blockHandler = "testHotRuleblockHandler")
    public String testHotRule(@RequestParam(value = "p1",required = false)Integer p1,
                              @RequestParam(value = "p2",required = false)Integer p2){
        return "----testhot";
    }
    public String testHotRuleblockHandler(Integer p1,
                                          Integer p2,
                                          BlockException blockException){
        return "被限流哩";
    }
}
