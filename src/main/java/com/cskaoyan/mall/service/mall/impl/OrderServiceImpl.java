package com.cskaoyan.mall.service.mall.impl;

import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.mapper.OrderMapper;
import com.cskaoyan.mall.service.mall.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Override
    public List<Order> queryOrderList(int orderStatusArray, String sort, String order, String orderSn, int userId) {
        List<Order> orders = orderMapper.queryOrderList(orderStatusArray, sort, order, orderSn, userId);
        return orders;
    }

    @Override
    public Order selectByPrimaryKey(String id) {
        Order order = orderMapper.selectByPrimaryKey(Integer.valueOf(id));
        return order;
    }

    @Override
    public int updateOrderStatuByOrderId(int order_satus, String ship_channel, String ship_sn) {
        return 0;
    }
}
