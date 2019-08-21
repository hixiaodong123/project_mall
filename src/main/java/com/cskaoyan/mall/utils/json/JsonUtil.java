package com.cskaoyan.mall.utils.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtil {

    private static List<Map<String, Object>> json2List(Object json) {
        JSONArray jsonArr = (JSONArray) json;
        List<Map<String, Object>> arrList = new ArrayList<>();
        for (int i = 0; i < jsonArr.size(); ++i) {
            String string = jsonArr.getString(i);
            arrList.add(strJson2Map(string));
        }
        return arrList;
    }

    public static Map<String, Object> strJson2Map(String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        Map<String, Object> resMap = new HashMap<>();
        Iterator<Entry<String, Object>> it = jsonObject.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> param = it.next();
            if (param.getValue() instanceof JSONObject) {
                resMap.put(param.getKey(), strJson2Map(param.getValue().toString()));
            } else if (param.getValue() instanceof JSONArray) {
                resMap.put(param.getKey(), json2List(param.getValue()));
            } else {
                resMap.put(param.getKey(), JSONObject.toJSONString(param.getValue(), SerializerFeature.WriteClassName));
            }
        }
        return resMap;
    }
}
