package com.atguigu.cloud.Service;

import com.atguigu.cloud.entities.Order;
import org.springframework.stereotype.Service;

public interface OrderService {
    /**
     * 新建订单
     * @param order
     */
    public void insertOrder(Order order);
}
