package com.atguigu.cloud.Api;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(value = "cloud-payment-service")
@FeignClient(value = "cloud-gateway")
public interface PayFeignApi {

    /**
     * 进行普通连通性测试
     * @param payDTO
     * @return
     */
    @PostMapping("/pay/add")
    public ResultData addPay(@RequestBody PayDTO payDTO);
    @GetMapping("/pay/get/{id}")
    public ResultData getById(@PathVariable("id") Integer id);
    @GetMapping("/pay/get/info")
    public String getVersionInfo();

    /**
     * 进行断路器测试
     * @param id
     * @return
     */
    @GetMapping("/pay/circuit/{id}")
    public String myCircuit(@PathVariable("id")Integer id);
    @GetMapping("/pay/ratelimit/{id}")
    public String myRatelimit(@PathVariable("id")Integer id);
    @GetMapping("/pay/micrometer/{id}")
    public String myMicrometer(@PathVariable("id")Integer id);

    /**
     * 进行网关测试
     * @return
     */
    @GetMapping("/pay/gateway/get/info")
    public ResultData<String> getGatewayInfo();
    @GetMapping("/pay/gateway/get/{id}")
    public ResultData getByIdG(@PathVariable("id")Integer id);
}
