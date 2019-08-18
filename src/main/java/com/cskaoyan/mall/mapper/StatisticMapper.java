package com.cskaoyan.mall.mapper;

import java.util.List;
import java.util.Map;

public interface StatisticMapper {

    List<Map<String,Object>> selectUserStatData();

    List<Map<String,Object>> selectOrderStatData();

    List<Map<String,Object>> selectGoodsStatData();
}
