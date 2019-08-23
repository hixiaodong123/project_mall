package com.cskaoyan.mall.service.user.impl;

import com.cskaoyan.mall.bean.Footprint;
import com.cskaoyan.mall.mapper.FootprintMapper;
import com.cskaoyan.mall.service.user.FootPrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FootPrintServiceImpl implements FootPrintService {
    @Autowired
    FootprintMapper footprintMapper;

    @Override
    public List<Footprint> listFootPrintByCondition(String userId, String goodsId, String sort, String order) {
        List<Footprint> footprintList = footprintMapper.listFootPrintByCondition(userId,goodsId,sort,order);
        return footprintList;
    }

    @Override
    public void insertFootPrint(Integer userId, Integer goodsId) {
        Footprint footprint = new Footprint();
        footprint.setUserId(userId);
        footprint.setGoodsId(goodsId);
        footprint.setDeleted(false);
        Date date = new Date(System.currentTimeMillis());
        footprint.setAddTime(date);
        footprint.setUpdateTime(date);
        footprintMapper.insert(footprint);
    }
}
