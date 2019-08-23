package com.cskaoyan.mall.service.goods.Imp;

import com.cskaoyan.mall.bean.GoodsAttribute;
import com.cskaoyan.mall.mapper.GoodsAttributeMapper;
import com.cskaoyan.mall.service.goods.GoodsAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsAttributeServiceImpl implements GoodsAttributeService {
    @Autowired
    GoodsAttributeMapper goodsAttributeMapper;

    @Override
    public List<GoodsAttribute> selectAttributeByGoodsId(int goodsId) {
        List<GoodsAttribute> attributes = goodsAttributeMapper.selectGoodsAttributeByGoodsId(goodsId);
        return attributes;
    }
}
