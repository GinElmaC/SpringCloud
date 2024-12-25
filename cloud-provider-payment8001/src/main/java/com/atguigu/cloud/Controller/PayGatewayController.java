package com.atguigu.cloud.Controller;

import cn.hutool.core.util.IdUtil;
import com.atguigu.cloud.Service.PayService;
import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.resp.ResultData;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;

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

    @GetMapping("/pay/gateway/filter")
    public ResultData<String> getGatewayFilter(HttpServletRequest request){
        String res = "";
        Enumeration<String> headers = request.getHeaderNames();
        while(headers.hasMoreElements()){
            String headName = headers.nextElement();
            String headValue = request.getHeader(headName);
            System.out.println("请求头:"+headName+"   "+"请求头值:"+headValue);
            if(headName.equalsIgnoreCase("X-Request-Mytest1") || headName.equalsIgnoreCase("X-Request-Mytest2")){
                res += (headName+" "+headValue);
            }
        }
        return ResultData.success("过滤器test："+"   "+res+"   "+IdUtil.simpleUUID());
    }
}
