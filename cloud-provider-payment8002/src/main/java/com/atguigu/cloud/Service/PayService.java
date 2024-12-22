package com.atguigu.cloud.Service;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.entities.Pay;

import java.util.List;

public interface PayService {
    public int add(PayDTO payDTO);
    public int delete(Integer id);
    public int update(Pay pay);

    public Pay getById(Integer id);
    public List<Pay> getAll();
}
