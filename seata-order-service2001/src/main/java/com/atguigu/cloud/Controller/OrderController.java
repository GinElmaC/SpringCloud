package com.atguigu.cloud.Controller;

import com.atguigu.cloud.Service.OrderService;
import com.atguigu.cloud.entities.Order;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/order/insert")
    public ResultData<String> insertOrder(Order order){
        orderService.insertOrder(order);
        return ResultData.success("操作成功，一切顺利"+order);
    }
}
