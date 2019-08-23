package com.cskaoyan.mall.service.goods.Imp;

import com.cskaoyan.mall.bean.GoodsProduct;
import com.cskaoyan.mall.mapper.GoodsProductMapper;
import com.cskaoyan.mall.service.goods.GoodsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsProductServiceImpl implements GoodsProductService {
    @Autowired
    GoodsProductMapper goodsProductMapper;

    @Override
    public List<GoodsProduct> selectGoodsProductsByGoodsId(int goodsId) {
        List<GoodsProduct> goodsProductList = goodsProductMapper.selectGoodsProductByGoodsId(goodsId);
        return goodsProductList;
    }

    @Override
    public int queryGoodsProductNumByGoodsId(Integer goodsId) {
        int i = goodsProductMapper.queryGoodsProductNumByGoodsId(goodsId);
        return i;
    }

    @Override
    public GoodsProduct selectByPrimaryKey(Integer id) {
        return goodsProductMapper.selectByPrimaryKey(id);
    }
}
