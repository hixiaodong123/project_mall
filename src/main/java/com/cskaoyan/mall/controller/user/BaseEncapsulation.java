package com.cskaoyan.mall.controller.user;

import com.cskaoyan.mall.bean.Footprint;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseEncapsulation {
    public static String judgeString(String param){
        if(param == null || param.trim().equals("")){
            param = "";
        }
        return param;
    }

    public static BaseResponseModel<Map<String, Object>> encapsulation(List list){
        PageInfo<Footprint> pageInfo = new PageInfo<>(list);
        //获得total参数
        long total = pageInfo.getTotal();
        //data参数类型为Map<String,Object>
        HashMap<String, Object> datamap = new HashMap<>();
        datamap.put("items",list);
        datamap.put("total",total);
        //配置待返回的BaseResponseModel
        BaseResponseModel<Map<String, Object>> responseModel = new BaseResponseModel<>();
        responseModel.setData(datamap);
        responseModel.setErrmsg("成功");
        responseModel.setErrno(0);
        return responseModel;
    }
}
