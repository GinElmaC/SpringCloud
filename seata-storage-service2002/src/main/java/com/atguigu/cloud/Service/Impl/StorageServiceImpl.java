package com.atguigu.cloud.Service.Impl;

import com.atguigu.cloud.Dao.StorageMapper;
import com.atguigu.cloud.Service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    private StorageMapper storageMapper;

    @Override
    public void descrease(Long productId,Integer count) {
        log.info("storage-service减库存开始");
        storageMapper.decrease(productId,count);
        log.info("storage-service减库存结束");
    }
}
