package com.atguigu.cloud.Controller;

import cn.hutool.core.util.IdUtil;
import com.atguigu.cloud.Service.PayService;
import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.resp.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayGatewayController {
    @Autowired
    private PayService payService;

    @GetMapping("/pay/gateway/get/{id}")
    public ResultData<Pay> getByIdG(@PathVariable("id")Integer id){
        Pay byId = payService.getById(id);
        return ResultData.success(byId);
    }

    @GetMapping("/pay/gateway/get/info")
    public ResultData<String> getGatewayInfo(){
        return ResultData.success("gateway info test: "+ IdUtil.simpleUUID());
    }
}
