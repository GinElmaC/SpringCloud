package com.atguigu.cloud.Api;

import com.atguigu.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-storage-service")
public interface StorageFeignApi {
    /**
     * 扣减仓库库存
     * @return
     */
    @PostMapping("/storage/decrease")
    public ResultData<String> decreaseStorage(@RequestParam("productId")Long productId,@RequestParam("count")Integer count);
}
