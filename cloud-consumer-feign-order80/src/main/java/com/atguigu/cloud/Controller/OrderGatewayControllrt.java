package com.atguigu.cloud.Controller;

import com.atguigu.cloud.Api.PayFeignApi;
import com.atguigu.cloud.resp.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderGatewayControllrt {
    @Autowired
    private PayFeignApi payFeignApi;

    @GetMapping("/feign/pay/gateway/get/{id}")
    public ResultData getByIdG(@PathVariable("id")Integer id){
        return payFeignApi.getByIdG(id);
    }

    @GetMapping("/feign/pay/gateway/get/info")
    public ResultData<String> getGatewayInfo(){
        return payFeignApi.getGatewayInfo();
    }
}
