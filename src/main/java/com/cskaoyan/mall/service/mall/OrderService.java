package com.cskaoyan.mall.service.mall;

import com.cskaoyan.mall.bean.Order;

import java.util.List;

public interface OrderService {
    List<Order> queryOrderList(int orderStatusArray, String sort, String order, String orderSn, int userId);

    Order selectByPrimaryKey(String id);
}
