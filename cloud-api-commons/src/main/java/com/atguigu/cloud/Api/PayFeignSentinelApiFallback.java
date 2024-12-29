package com.atguigu.cloud.Api;

import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.resp.ReturnCodenum;
import org.springframework.stereotype.Component;

@Component
public class PayFeignSentinelApiFallback implements PayFeignSentinelApi{
    @Override
    public ResultData getPayByOrderNo(String orderNo) {
        return ResultData.fail(ReturnCodenum.RC500.getCode(), "对方服务器暂不可用，触发服务降级");
    }
}
