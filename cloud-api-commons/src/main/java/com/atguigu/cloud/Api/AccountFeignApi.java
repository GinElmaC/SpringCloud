package com.atguigu.cloud.Api;

import com.atguigu.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-account-service")
public interface AccountFeignApi {
    /**
     * 扣减账户余额
     * @return
     */
    @PostMapping("/account/decrease")
    public ResultData<String> decreaseAccount(@RequestParam("userId")Long userId,@RequestParam("money")Long money);
}
