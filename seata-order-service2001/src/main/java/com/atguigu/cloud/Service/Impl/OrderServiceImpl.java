package com.atguigu.cloud.Service.Impl;

import com.atguigu.cloud.Api.AccountFeignApi;
import com.atguigu.cloud.Api.StorageFeignApi;
import com.atguigu.cloud.Dao.OrderMapper;
import com.atguigu.cloud.Service.OrderService;
import com.atguigu.cloud.entities.Order;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private StorageFeignApi storageFeignApi;
    @Autowired
    private AccountFeignApi accountFeignApi;
    /**
     * 新建订单  减库存  扣余额  该订单状态
     * @param order
     */
    @Override
    //name指定这个全局事务的名称，只要不重复就行
    //rollbackFor指定了在遇到哪些错误时会进行回滚，这里指定的是所有错误Exception
    @GlobalTransactional(name = "zzyy-create-order",rollbackFor = Exception.class)
    public void insertOrder(Order order) {
        //检查XID,只是为了调试方便
        String xid = RootContext.getXID();

        log.info("开始新建订单"+xid);
        order.setStatus(0);
        //向数据库插入订单信息
        int res = orderMapper.insert(order);

        if(res != 0){
            Order orderfromDB = orderMapper.selectByPrimaryKey(order.getId());
            //修改库存数量
            log.info("修改库存数量");
            storageFeignApi.decreaseStorage(orderfromDB.getProductId(),orderfromDB.getCount());
            //修改账户信息
            log.info("修改账户信息");
            accountFeignApi.decreaseAccount(orderfromDB.getUserId(),orderfromDB.getMoney());
            //更新订单信息
            //更新订单数量
            log.info("修改订单状态");
            orderfromDB.setStatus(1);
            log.info("更新订单信息");
            orderMapper.updateByPrimaryKey(orderfromDB);
            int result = orderMapper.updateByPrimaryKey(orderfromDB);
        }
        log.info("操作结束，一切顺利");
    }
}
