package com.cskaoyan.mall.service.wx_service.cart.impl;

import com.cskaoyan.mall.mapper.GoodsProductMapper;
import com.cskaoyan.mall.service.wx_service.cart.GoodsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsProductServiceImpl implements GoodsProductService {
    @Autowired
    GoodsProductMapper goodsProductMapper;


    @Override
    public int queryGoodsProductNumByGoodsId(Integer goodsId) {
        int i = goodsProductMapper.queryGoodsProductNumByGoodsId(goodsId);
        return i;
    }
}
