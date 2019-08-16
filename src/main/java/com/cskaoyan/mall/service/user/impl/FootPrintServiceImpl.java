package com.cskaoyan.mall.service.user.impl;

import com.cskaoyan.mall.bean.Footprint;
import com.cskaoyan.mall.mapper.FootprintMapper;
import com.cskaoyan.mall.service.user.FootPrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FootPrintServiceImpl implements FootPrintService {
    @Autowired
    FootprintMapper footprintMapper;

    @Override
    public List<Footprint> listFootPrintByCondition(String userId, String goodsId) {
        List<Footprint> footprintList = footprintMapper.listFootPrintByCondition(userId,goodsId);
        return footprintList;
    }
}
