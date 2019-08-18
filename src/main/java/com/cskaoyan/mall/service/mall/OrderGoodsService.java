package com.cskaoyan.mall.service.mall;

import com.cskaoyan.mall.bean.OrderGoods;

import java.util.List;

public interface OrderGoodsService {
    List<OrderGoods> selectByOrderId(int orderId);
}
