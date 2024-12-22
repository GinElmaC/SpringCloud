package com.atguigu.cloud.Controller;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * 消费者80调用8001
 */
@RestController
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;
    //写死了
    //public static final String PaymentSrv_URL = "http://localhost:8001";
    //不写死
    //服务注册中心上的微服务名称
    public static final String PaymentSrv_URL = "http://cloud-payment-service";//服务注册中心上的微服务名称

    /**
     * 新增
     * @param payDTO
     * @return
     */
    @PostMapping("/consumer/pay/add")
    public ResultData addOrder(PayDTO payDTO){
        return restTemplate.postForObject(PaymentSrv_URL+"/pay/add",payDTO,ResultData.class);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/consumer/pay/del/{id}")
    public void deleteOrder(@PathVariable("id") Integer id){
        restTemplate.delete(PaymentSrv_URL+"/pay/del/"+id,id);
    }

    /**
     * 修改
     * @param payDTO
     * @return
     */
    @PutMapping("/consumer/pay/update")
    public ResultData updateOrder(PayDTO payDTO){
        return restTemplate.postForObject(PaymentSrv_URL+"/pay/update",payDTO,ResultData.class);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/consumer/pay/get/{id}")
    public ResultData<PayDTO> selectById(@PathVariable("id") Integer id){
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/" + id, ResultData.class, id);
    }

    /**
     * 查询所有
     * @return
     */
    @GetMapping("/consumer/pay/get")
    public ResultData selectAll(){
        return restTemplate.getForObject(PaymentSrv_URL+"/pay/get",ResultData.class);
    }

    @GetMapping("/consumer/pay/get/info")
    public String getInfo(){
        return restTemplate.getForObject(PaymentSrv_URL+"/pay/get/info",String.class);
    }
}
