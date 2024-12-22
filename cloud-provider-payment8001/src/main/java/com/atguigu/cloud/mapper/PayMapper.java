package com.atguigu.cloud.mapper;

import com.atguigu.cloud.entities.Pay;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Pay record);

    int insertSelective(Pay record);

    Pay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Pay record);

    int updateByPrimaryKey(Pay record);

    List<Pay> selectAll();
}