package com.atguigu.cloud.Controller;

import com.atguigu.cloud.Service.PayService;
import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import java.util.List;

@RestController
@Tag(name = "支付服务模块",description = "支付CRUD")
public class PayController {
    @Autowired
    private PayService payService;

    @PostMapping("/pay/add")
    @Operation(summary = "新增",description = "新增支付流水方法")
    public ResultData<String> addPay(@RequestBody PayDTO payDTO){
        System.out.println(payDTO);
        int add = payService.add(payDTO);
        return ResultData.success("成功插入");
    }

    @DeleteMapping("/pay/del/{id}")
    @Operation(summary = "删除",description = "根据id删除")
    public ResultData<String> delete(@PathVariable("id") Integer id){
        System.out.println("删除"+id);
        int delete = payService.delete(id);
        return ResultData.success("成功删除");
    }

    @PutMapping("/pay/update")
    @Operation(summary = "修改",description = "传入各项参数")
    public ResultData<String> update(@RequestBody PayDTO payDTO){
        System.out.println("修改"+payDTO);
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO,pay);
        int update = payService.update(pay);
        return ResultData.success("成功修改");
    }

    @GetMapping("/pay/get/{id}")
    @Operation(summary = "查一个",description = "根据id查询")
    public ResultData<Pay> getById(@PathVariable("id") Integer id){
        System.out.println("查找"+id);
        if(id<0){
            throw new RuntimeException("id不可以是负数");
        }
        Pay pay = payService.getById(id);
        System.out.println("");
        return ResultData.success(pay);
    }

    @GetMapping("/pay/get")
    @Operation(summary = "查所有",description = "查询所有，返回list")
    public ResultData<List<Pay>> getAll(){
        System.out.println("查询全部");
        List<Pay> list=  payService.getAll();
        return ResultData.success(list);
    }

    @Value("${server.port}")
    private String port;
    @GetMapping("pay/get/info")
    public String getVersionInfo(@Value("${atguigu.info}") String info){
        return info+"   "+port;
    }
}
