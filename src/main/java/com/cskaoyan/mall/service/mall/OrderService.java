package com.cskaoyan.mall.service.mall;

import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.bean.OrderGoods;

import java.util.List;
import java.util.Map;

public interface OrderService {
    List<Order> queryOrderList(int[] orderStatusArray, String sort, String order, String orderSn, int userId);

    Order selectByPrimaryKey(String id);

    int updateStatuAs301ByOrderId(int orderId, String shipChannel, String shipSn);

    int updateStatuAs203ByOrderId(int orderId, int refundMoney);

    long queryOrderStatusNum(int status,int userId);

    int updateOrderStatuByOrderId(int order_satus, String ship_channel, String ship_sn);

    Map<String,Object> returnOrderListType1(int showType,int page, int size,int userId);

    List<OrderGoods> returnGoodsList(int id,int page, int size);

    Map<String,Object> getOrderInfo(int id);

    int cancelOlder(int status ,int orderId);

    int deleteOrder(int orderId);

}
