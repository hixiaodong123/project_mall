package com.cskaoyan.mall.service.mall;

import com.cskaoyan.mall.bean.OrderGoods;

import java.util.List;

public interface OrderGoodsService {
    List<OrderGoods> selectByOrderId(int orderId);

    List<OrderGoods> getOrderGoodsListByOrderId(int orderId);

    OrderGoods queryOrderGoodsByOrderIdAndGoodsId(int orderId,int goodsId);
}
