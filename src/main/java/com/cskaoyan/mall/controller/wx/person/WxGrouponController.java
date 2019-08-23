package com.cskaoyan.mall.controller.wx.person;

import com.cskaoyan.mall.service.popularize.GrouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("wx")
public class WxGrouponController {
    @Autowired
    GrouponService grouponService;

    @RequestMapping("groupon/my")
    public Map<String,Object> getGrouponList(int showType){
        return grouponService.returnGrouponList(showType);
    }

    @RequestMapping("groupon/detail")
    public Map<String,Object> returnGrouponDetail(int grouponId){
        return grouponService.returnGrouponDetail(grouponId);
    }
}
