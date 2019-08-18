package com.cskaoyan.mall.utils;

import java.util.HashMap;
import java.util.Map;

public class ReturnMapUntil {
    public static Map<String,Object> returnMap(Map<String,Object> map1,String errmsg,int errno){
        HashMap<String, Object> map = new HashMap<>();
        map.put("data",map1);
        map.put("errmsg","成功");
        map.put("errno",0);
        return map;
    }
}
