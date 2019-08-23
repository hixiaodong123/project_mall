package com.cskaoyan.mall.service.mall.impl;

import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.bean.OrderExample;
import com.cskaoyan.mall.bean.OrderGoods;
import com.cskaoyan.mall.bean.OrderGoodsExample;
import com.cskaoyan.mall.mapper.OrderGoodsMapper;
import com.cskaoyan.mall.mapper.OrderMapper;
import com.cskaoyan.mall.service.mall.OrderService;
import com.cskaoyan.mall.utils.ReturnMapUntil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderGoodsMapper orderGoodsMapper;
    @Autowired
    OrderService orderService;

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
    public long queryOrderStatusNum(int status,int userId) {
        return orderMapper.queryOrderStatusNum(status,userId);
    }

    @Override
    public int updateOrderStatuByOrderId(int order_satus, String ship_channel, String ship_sn) {
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

    @Override
    public Map<String, Object> returnOrderListType1(int showType, int page, int size,int userId) {
        List<Integer> ids = new ArrayList<>();
        if (showType != 1) {
            ids = (ArrayList<Integer>) orderMapper.queryOrderIdByStatus(showType,userId);
        } else {
            for (int i = 1; i <= 4; i++) {
                List<Integer> id = orderMapper.queryOrderIdByStatus(i * 100 + 1,userId);
                ids.addAll(id);
            }
        }
        PageHelper.startPage(page, size);
        List<Object> list = new ArrayList<>();
        OrderExample example = new OrderExample();
        example.createCriteria().andIdIn(ids);
        PageHelper.startPage(page,size);
        List<Order> orders = orderMapper.selectByExample(example);
        for (Order order : orders) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("actualPrice", order.getActualPrice());
            map.put("id", order.getId());
            map.put("isGroupin", !order.getCouponPrice().equals(0));
            map.put("orderSn", order.getOrderSn());
            switch (order.getOrderStatus()) {
                case 101:
                    map.put("orderStatusText", "未付款");
                    break;
                case 201:
                    map.put("orderStatusText", "已付款");
                    break;
                case 301:
                    map.put("orderStatusText", "已发货");
                    break;
                case 401:
                    map.put("orderStatusText", "已收货");
                    break;
            }
            List<OrderGoods> orderGoods = orderGoodsMapper.selectByOrderId(order.getId());
            //List<OrderGoods> orderGoods = orderService.returnGoodsList(id, page, size);
            List<Object> list1 = new ArrayList<>();
            for (OrderGoods orderGood : orderGoods) {
                Map<String, Object> hashMap = new HashMap<>();
                hashMap.put("goodsName", orderGood.getGoodsName());
                hashMap.put("id", orderGood.getId());
                hashMap.put("number", orderGood.getNumber());
                hashMap.put("picUrl", orderGood.getPicUrl());
                list1.add(hashMap);
            }
            Map<String, Object> map1 = new HashMap<>();
            map1.put("cancel", false);
            map1.put("comment", false);
            map1.put("confirm", false);
            map1.put("delete", false);
            map1.put("pay", false);
            map1.put("rebuy", false);
            map1.put("refund", false);
            switch (showType) {
                case 101:
                    map1.put("cancel", true);
                    map1.put("pay", true);
                    break;
                case 201:
                    map1.put("refund", true);
                    break;
                case 301:
                    map1.put("confirm", true);
                    break;
                case 401:
                    map1.put("comment", true);
                    map1.put("delete", true);
                    map1.put("rebuy", true);
                    break;
            }
            map.put("goodsList", list1);
            map.put("handleOption", map1);
            list.add(map);
        }
        Map<String, Object> map2 = new HashMap<>();
        map2.put("count", ids.size());
        map2.put("data", list);
        map2.put("tatalPages", (ids.size() / size + 1));
        return ReturnMapUntil.returnMap(map2, "成功", 0);
    }

    @Override
    public List<OrderGoods> returnGoodsList(int id, int page, int size) {
        PageHelper.startPage(page, size);
        List<OrderGoods> orderGoods = orderGoodsMapper.selectByOrderId(id);
        return orderGoods;

    }

    @Override
    public Map<String, Object> getOrderInfo(int id) {
        Order order = orderMapper.selectByPrimaryKey(id);
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("actualPrice", order.getActualPrice());
        map1.put("addTime", order.getAddTime());
        map1.put("address", order.getAddress());
        map1.put("consignee", order.getConsignee());
        map1.put("couponPrice", order.getCouponPrice());
        map1.put("freightPrice", order.getFreightPrice());
        map1.put("goodsPrice", order.getGoodsPrice());
        map1.put("id", order.getId());
        map1.put("mobile", order.getMobile());
        map1.put("orderSn", order.getOrderSn());
        switch (order.getOrderStatus()) {
            case 101:
                map1.put("orderStatusText", "未付款");
                break;
            case 102:
                map1.put("orderStatusText", "已取消");
                break;
            case 103:
                map1.put("orderStatusText", "已取消");
                break;
            case 201:
                map1.put("orderStatusText", "已付款");
                break;
            case 202:
                map1.put("orderStatusText", "申请退款");
                break;
            case 203:
                map1.put("orderStatusText", "已退款");
                break;
            case 301:
                map1.put("orderStatusText", "已发货");
                break;
            case 401:
                map1.put("orderStatusText", "已收货");
                break;
            case 402:
                map1.put("orderStatusText", "已收货");
                break;
        }
        Map<String, Object> map2 = new HashMap<>();
        map2.put("cancel", false);
        map2.put("comment", false);
        map2.put("pay", false);
        map2.put("delete", false);
        map2.put("rebuy", false);
        map2.put("refund", false);
        map2.put("confirm", false);
        switch (order.getOrderStatus()) {
            case 101:
                map2.put("pay", true);
                map2.put("cancel", true);
                break;
            case 102:
                map2.put("cancel", true);
                break;
            case 103:
                map2.put("cancel", true);
                break;
            case 201:
                map2.put("refund", true);
                break;
            case 203:
                map2.put("delete", true);
                break;
            case 301:
                map2.put("confirm", true);
                break;
            case 401:
                map2.put("comment", true);
                map2.put("delete", true);
                map2.put("rebuy", true);
                break;
        }
        map1.put("handleOption",map2);
        return map1;
    }

    @Override
    public int cancelOlder(int status,int orderId) {
        return orderMapper.updataStatusByOrderId(status,orderId);
    }

    @Override
    public int deleteOrder(int orderId) {
        return orderMapper.updateDeleteByOrderId(true, orderId);
    }
}
