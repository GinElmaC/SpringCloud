package com.atguigu.cloud.Controller;

import com.atguigu.cloud.Service.StorageService;
import com.atguigu.cloud.resp.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {
    @Autowired
    private StorageService storageService;

    @PostMapping("/storage/decrease")
    public ResultData<String> decreaseStorage(@RequestParam("productId")Long productId, @RequestParam("count")Integer count){
        storageService.descrease(productId,count);
        return ResultData.success("库存操作成功");
    }
}
