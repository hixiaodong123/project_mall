package com.cskaoyan.mall.service.statistics.impl;

import com.cskaoyan.mall.mapper.StatisticMapper;
import com.cskaoyan.mall.service.statistics.StatisticsService;
import com.cskaoyan.mall.utils.ReturnMapUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    StatisticMapper statisticMapper;
    @Override
    public Map<String, Object> returnUserStatData() {
        HashMap<String, Object> map1 = new HashMap<>();
        List<Map<String,Object>> list=statisticMapper.selectUserStatData();
        String[] columns={"day","users"};
        map1.put("columns",columns);
        map1.put("rows",list);
        return ReturnMapUntil.returnMap(map1,"成功",0);
    }

    @Override
    public Map<String, Object> returnOrderStatData() {
        HashMap<String, Object> map1 = new HashMap<>();
        List<Map<String,Object>> list=statisticMapper.selectOrderStatData();
        String[] columns={"day","orders","customers","amount","pcr"};
        map1.put("columns",columns);
        map1.put("rows",list);
        return ReturnMapUntil.returnMap(map1,"成功",0);
    }

    @Override
    public Map<String, Object> returnGoodsStatData() {
        HashMap<String, Object> map1 = new HashMap<>();
        List<Map<String,Object>> list=statisticMapper.selectGoodsStatData();
        String[] columns={"day","orders","products","amount"};
        map1.put("columns",columns);
        map1.put("rows",list);
        return ReturnMapUntil.returnMap(map1,"成功",0);
    }
}
