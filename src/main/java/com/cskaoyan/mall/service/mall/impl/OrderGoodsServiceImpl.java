package com.cskaoyan.mall.service.mall.impl;

import com.cskaoyan.mall.bean.OrderGoods;
import com.cskaoyan.mall.mapper.OrderGoodsMapper;
import com.cskaoyan.mall.service.mall.OrderGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderGoodsServiceImpl implements OrderGoodsService {
    @Autowired
    OrderGoodsMapper orderGoodsMapper;


    @Override
    public List<OrderGoods> selectByOrderId(int orderId) {
        List<OrderGoods> orderGoods = orderGoodsMapper.selectByOrderId(orderId);
        return orderGoods;
    }
}
