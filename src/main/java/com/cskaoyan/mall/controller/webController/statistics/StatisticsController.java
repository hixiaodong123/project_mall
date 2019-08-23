package com.cskaoyan.mall.controller.webController.statistics;

import com.cskaoyan.mall.service.statistics.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin/stat")
public class StatisticsController {
    @Autowired
    StatisticsService statisticsService;

    @RequestMapping("user")
    public Map<String,Object> returnUserStat(){
        return  statisticsService.returnUserStatData();
    }

    @RequestMapping("order")
    public Map<String,Object> returnOrderStat(){
        return  statisticsService.returnOrderStatData();
    }

    @RequestMapping("goods")
    public Map<String,Object> returnGoodsStat(){
        return  statisticsService.returnGoodsStatData();
    }
}
