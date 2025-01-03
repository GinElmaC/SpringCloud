package com.atguigu.cloud.Service.Impl;

import com.atguigu.cloud.Dao.AccountMapper;
import com.atguigu.cloud.Service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public void decreaseAccount(Long userId, Long money) {
        log.info("开始修改账户信息");
        accountMapper.decreaseMoney(userId, money);
        runtimeException();
//        int a = 10/0;
        log.info("修改账户金额完成");
    }
    //人造超时异常
    public void runtimeException(){
        try {
            TimeUnit.SECONDS.sleep(65);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
