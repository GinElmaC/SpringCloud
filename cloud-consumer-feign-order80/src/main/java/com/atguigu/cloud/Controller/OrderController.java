package com.atguigu.cloud.Controller;

import cn.hutool.core.date.DateUtil;
import com.atguigu.cloud.Api.PayFeignApi;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.resp.ReturnCodenum;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * 消费者80调用8001
 */
@RestController
public class OrderController {
    @Resource
    private PayFeignApi payFeignApi;

    @PostMapping("/feign/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO){
        ResultData resultData = payFeignApi.addPay(payDTO);
        return resultData;
    }

    //TODO这个方法会报错，是为了学习OpenFeign的超时控制搞得
    @GetMapping("/feign/pay/get/{id}")
    public ResultData getById(@PathVariable("id") Integer id){
        System.out.println("支付微服务远程调用");
        ResultData resultData = null;
        try {
            System.out.println("调用开始---"+ DateUtil.now());
            resultData = payFeignApi.getById(id);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("调用结束---"+DateUtil.now());
            ResultData.fail(ReturnCodenum.RC500.getCode(),e.getMessage());
        }
        return resultData;
    }

    @GetMapping("/feign/pay/get/info")
    public String getVersionInfo(){
        return payFeignApi.getVersionInfo();
    }
}
