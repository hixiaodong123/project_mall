package com.cskaoyan.mall.controller.wxcontroller.user;

import com.cskaoyan.mall.mapper.OrderMapper;
import com.cskaoyan.mall.service.mall.OrderService;
import com.cskaoyan.mall.utils.ReturnMapUntil;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("wx")
public class WxUserController {
    @Autowired
    OrderService orderService;

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

    /*@RequestMapping("order/list")
    public Map<String,Object> returnOrderList(int showType,int page,int size){


    }*/
}
