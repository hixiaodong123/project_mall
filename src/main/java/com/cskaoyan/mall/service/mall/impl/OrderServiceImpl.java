package com.cskaoyan.mall.service.mall.impl;

import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.mapper.OrderMapper;
import com.cskaoyan.mall.service.mall.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Override
    public List<Order> queryOrderList(int[] orderStatusArray, String sort, String order, String orderSn, int userId) {
        List<Order> orderList = new ArrayList<>();
        for(int statue : orderStatusArray) {
            List<Order> orders = orderMapper.queryOrderList(statue, sort, order, orderSn, userId);
            for(Order order1 : orders) {
                orderList.add(order1);
            }
        }
        return orderList;
    }

    @Override
    public Order selectByPrimaryKey(String id) {
        Order order = orderMapper.selectByPrimaryKey(Integer.valueOf(id));
        return order;
    }

    @Override
    public long queryOrderStatusNum(int status) {
        orderMapper.queryOrderStatusNum(status);
        return 0;
    }

    @Override
    public int updateStatuAs301ByOrderId(int orderId, String shipChannel, String shipSn) {
        int i = orderMapper.updateStatuAs301ByOrderId(orderId, shipChannel, shipSn);
        return i;
    }

    @Override
    public int updateStatuAs203ByOrderId(int orderId, int refundMoney) {
        int i = orderMapper.updateStatuAs203ByOrderId(orderId, refundMoney);
        return i;
    }
}
