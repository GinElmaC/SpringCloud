package com.atguigu.cloud.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//测试Sentinel的权限规则
@RestController
@Slf4j
public class EmpowerController {

    @GetMapping("/empower")
    public String requestSentinel(){
        log.info("测试权限规则");
        return "Sentinel授权规则";
    }
}
