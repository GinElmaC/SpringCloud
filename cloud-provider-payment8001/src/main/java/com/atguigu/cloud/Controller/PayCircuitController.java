package com.atguigu.cloud.Controller;

import cn.hutool.core.util.IdUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class PayCircuitController {

    //TODO专门测试断路器的
    @GetMapping("/pay/circuit/{id}")
    public String myCircuit(@PathVariable("id")Integer id){
        if(id < 0){
            throw new RuntimeException("id不能是负数");
        }
        if(id == 999){
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "HEllo,Circuit"+id+"\n"+ IdUtil.simpleUUID();
    }
}
