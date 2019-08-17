package com.cskaoyan.mall.utils;

import java.util.HashMap;
import java.util.Map;

public class GoodsReturnUntil {

    public static Map<String,Object> goodsReturnUtil(boolean b){
        HashMap<String, Object> map = new HashMap<>();
        if(b==true){
            map.put("errmsg","成功");
            map.put("errno",0);
        }else{
            map.put("errmsg","失败");
            map.put("errno",-1);
        }
        return map;
    }
}
