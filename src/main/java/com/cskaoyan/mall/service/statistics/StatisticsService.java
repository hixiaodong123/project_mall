package com.cskaoyan.mall.service.statistics;

import java.util.Map;

public interface StatisticsService {

    Map<String,Object> returnUserStatData();

    Map<String,Object> returnOrderStatData();

    Map<String,Object> returnGoodsStatData();
}
