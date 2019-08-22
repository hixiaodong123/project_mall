package com.cskaoyan.mall.service.mall;

import com.cskaoyan.mall.bean.Order;

import java.util.List;

public interface OrderService {
    List<Order> queryOrderList(int[] orderStatusArray, String sort, String order, String orderSn, int userId);

    Order selectByPrimaryKey(String id);

    int updateStatuAs301ByOrderId(int orderId, String shipChannel, String shipSn);

    int updateStatuAs203ByOrderId(int orderId, int refundMoney);
    long queryOrderStatusNum(int status);
}
