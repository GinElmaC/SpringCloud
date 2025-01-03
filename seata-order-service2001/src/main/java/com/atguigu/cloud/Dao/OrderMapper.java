package com.atguigu.cloud.Dao;

import com.atguigu.cloud.entities.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);
    Order selectByBody(Order order);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

}