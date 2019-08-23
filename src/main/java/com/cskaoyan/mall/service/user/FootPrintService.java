package com.cskaoyan.mall.service.user;

import com.cskaoyan.mall.bean.Footprint;

import java.util.List;

public interface FootPrintService {

    List<Footprint> listFootPrintByCondition(String userId, String goodsId, String sort, String order);

    void insertFootPrint(Integer userId, Integer id);
}
