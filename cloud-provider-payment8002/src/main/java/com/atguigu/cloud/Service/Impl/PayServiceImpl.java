package com.atguigu.cloud.Service.Impl;

import com.atguigu.cloud.Service.PayService;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.mapper.PayMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.additional.update.differ.UpdateByDifferMapper;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class PayServiceImpl implements PayService {
    @Resource
    private PayMapper payMapper;

    @Override
    public int add(PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO,pay);
        pay.setCreateTime(new Date());
        pay.setUpdateTime(new Date());
        return payMapper.insertSelective(pay);
    }

    @Override
    public int delete(Integer id) {
        return payMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Pay pay) {
        return payMapper.updateByPrimaryKeySelective(pay);
    }

    @Override
    public Pay getById(Integer id) {
        return payMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Pay> getAll() {
        return payMapper.selectAll();
    }
}
