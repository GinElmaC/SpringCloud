package com.atguigu.cloud.Controller;

import com.atguigu.cloud.Service.AccountService;
import com.atguigu.cloud.resp.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/account/decrease")
    public ResultData<String> decreaseAccount(@RequestParam("userId")Long userId, @RequestParam("money")Long money){
        accountService.decreaseAccount(userId,money);
        return ResultData.success("修改账户金额操作成功");
    }
}
