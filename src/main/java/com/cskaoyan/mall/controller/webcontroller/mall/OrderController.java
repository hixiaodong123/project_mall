package com.cskaoyan.mall.controller.webcontroller.mall;

import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.bean.OrderGoods;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.bean.mall.OrderShip;
import com.cskaoyan.mall.bean.page.PageBean;
import com.cskaoyan.mall.service.mall.OrderGoodsService;
import com.cskaoyan.mall.service.mall.OrderService;
import com.cskaoyan.mall.service.user.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    OrderGoodsService orderGoodsService;

    @Autowired
    UserService userService;

    @RequestMapping("order/list")
    public BaseResponseModel list(int page, int limit, int[] orderStatusArray, String sort, String order, String orderSn, Integer userId) {
        if(orderStatusArray == null) {
            orderStatusArray = new int[1];
        }
        if(userId == null) {
            userId = 0;
        }
        PageHelper.startPage(page, limit);
        List<Order> orders = orderService.queryOrderList(orderStatusArray, sort, order, orderSn, userId);
        PageInfo<Order> pageInfo = new PageInfo<>(orders);
        PageBean<Order> data = new PageBean<>(orders, pageInfo.getTotal());
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        baseResponseModel.setErrmsg("成功");
        baseResponseModel.setErrno(0);
        baseResponseModel.setData(data);
        return baseResponseModel;
    }

    @RequestMapping("order/detail")
    public BaseResponseModel detail(String id) {
        Order order = orderService.selectByPrimaryKey(id);
        List<OrderGoods> orderGoods = orderGoodsService.selectByOrderId(order.getId());
        User user = userService.selectByPrimaryKey(order.getUserId());
        Map data = new HashMap();
        data.put("order",order);
        data.put("orderGoods",orderGoods);
        data.put("user",user);
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        baseResponseModel.setData(data);
        baseResponseModel.setErrno(0);
        baseResponseModel.setErrmsg("成功");
        return baseResponseModel;
    }

    @RequestMapping("order/ship")
    public BaseResponseModel ship(@RequestBody OrderShip orderShip) {
        int i = orderService.updateStatuAs301ByOrderId(orderShip.getOrderId(), orderShip.getShipChannel(), orderShip.getShipSn());
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        if (i == 1) {
            baseResponseModel.setErrno(0);
            baseResponseModel.setErrmsg("成功");
        }
        return baseResponseModel;
    }

    @RequestMapping("order/refund")
    public BaseResponseModel refund(@RequestBody OrderShip orderShip) {
        int i = orderService.updateStatuAs203ByOrderId(orderShip.getOrderId(), orderShip.getRefundMoney());
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        if (i == 1) {
            baseResponseModel.setErrno(0);
            baseResponseModel.setErrmsg("成功");
        } else {
            baseResponseModel.setErrmsg("订单退款失败");
            baseResponseModel.setErrno(621);
        }
        return baseResponseModel;
    }
}
