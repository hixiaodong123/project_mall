package com.cskaoyan.mall.controller.wx.user;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.bean.OrderGoods;
import com.cskaoyan.mall.mapper.OrderGoodsMapper;
import com.cskaoyan.mall.service.goods.CommentService;
import com.cskaoyan.mall.service.mall.OrderGoodsService;
import com.cskaoyan.mall.service.mall.OrderService;
import com.cskaoyan.mall.utils.ReturnMapUntil;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("wx")
public class WxUserController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderGoodsService orderGoodsService;
    @Autowired
    CommentService commentService;

    @RequestMapping("user/index")
    public Map<String,Object> returnUserIndex(){
        long unpaid = orderService.queryOrderStatusNum(101);
        long unship = orderService.queryOrderStatusNum(201);
        long unrecv = orderService.queryOrderStatusNum(301);
        long uncomment = orderService.queryOrderStatusNum(401);
        HashMap<String, Object> map1 = new HashMap<>();
        HashMap<String, Object> map2 = new HashMap<>();
        map1.put("unpaid",unpaid);
        map1.put("unship",unship);
        map1.put("unrecv",unrecv);
        map1.put("uncomment",uncomment);
        map2.put("order",map1);
        return ReturnMapUntil.returnMap(map2,"成功",0);
    }

    @RequestMapping("order/list")
    public Map<String,Object> returnOrderList(int showType,int page,int size){
        int showType1 = showType*100 + 1;
        return orderService.returnOrderListType1(showType1,page,size);
    }

    @RequestMapping("order/detail")
    public Map<String,Object> returnOrderDetail(int orderId){
        List<OrderGoods> orderGoods = orderGoodsService.getOrderGoodsListByOrderId(orderId);
        Map<String,Object> map1= orderService.getOrderInfo(orderId);
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("orderGoods",orderGoods);
        map2.put("orderInfo",map1);
        return ReturnMapUntil.returnMap(map2,"成功",0);
    }

    @RequestMapping("order/cancel")
    public Map<String,Object> cancelOrder(@RequestBody Map<String,Object> orderId){
        int id = (int) orderId.get("orderId");
        int i=orderService.cancelOlder(102,id);
        if(i==1){
            return ReturnMapUntil.returnMap(null,"成功",0);
        }else{
            return ReturnMapUntil.returnMap(null,"取消订单失败",0);
        }
    }

    @RequestMapping("order/refund")
    public Map<String,Object> refundOrder(@RequestBody Map<String,Object> orderId){
        int id = (int) orderId.get("orderId");
        int i=orderService.cancelOlder(202,id);
        if(i==1){
            return ReturnMapUntil.returnMap(null,"成功",0);
        }else{
            return ReturnMapUntil.returnMap(null,"取消订单失败",0);
        }
    }

    @RequestMapping("order/confirm")
    public Map<String,Object> confirmOrder(@RequestBody Map<String,Object> orderId){
        int id = (int) orderId.get("orderId");
        int i=orderService.cancelOlder(401,id);
        if(i==1){
            return ReturnMapUntil.returnMap(null,"成功",0);
        }else{
            return ReturnMapUntil.returnMap(null,"取消订单失败",0);
        }
    }
    //order/goods
    @RequestMapping("order/goods")
    public Map<String,Object> returnOrderGoods(int orderId,int goodsId){
        OrderGoods orderGoods = orderGoodsService.queryOrderGoodsByOrderIdAndGoodsId(orderId, goodsId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("data",orderGoods);
        map.put("errmsg","成功");
        map.put("errno",0);
        return map;
    }

    @RequestMapping("order/comment")
    public Map<String,Object> insertComment(@RequestBody Map<String,Object> map){
        Date date = new Date();
        Comment comment = new Comment();
        comment.setValueId((Integer) map.get("orderGoodsId"));
        comment.setType((byte) 0);
        comment.setContent((String) map.get("content"));
        comment.setUserId(1);
        if(map.get("picUrls")==null){
            comment.setHasPicture(false);
        }else{
            comment.setHasPicture(true);
        }
        ArrayList<String> strings = (ArrayList<String>) map.get("picUrls");
        String[] picUrls={};
        int j=0;
        for (String string : strings) {
            picUrls[j++] = string;
        }
        comment.setPicUrls(picUrls);
        /*comment.setStar((Short) map.get("star"));*/
        Integer star = (Integer) map.get("star");
        short value = star.shortValue();
        comment.setStar(value);
        comment.setAddTime(date);
        comment.setUpdateTime(date);
        comment.setDeleted(false);
        int i = commentService.insertComment(comment);
        if(i==1){
            return ReturnMapUntil.returnMap(null,"成功",0);
        }else{
            return ReturnMapUntil.returnMap(null,"评论失败",0);
        }
    }

    @RequestMapping("order/delete")
    public Map<String,Object> deleteOrder(@RequestBody() Map<String,Object> map){
        Integer orderId = (Integer) map.get("orderId");
        int i = orderService.deleteOrder(orderId);
        if(i==1){
            return ReturnMapUntil.returnMap(null,"成功",0);
        }else{
            return ReturnMapUntil.returnMap(null,"删除订单失败",0);
        }

    }


}
